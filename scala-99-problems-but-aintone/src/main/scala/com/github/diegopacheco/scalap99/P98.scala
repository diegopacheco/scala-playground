package com.github.diegopacheco.scalap99

// P98(***) Nonograms .
//
// Around 1994, a certain kind of puzzles was very popular in England. The “Sunday Telegraph” newspaper
// wrote: “Nonograms are puzzles from Japan and are currently published each week only in The Sunday Telegraph.
// Simply use your logic and skill to complete the grid and reveal a picture or diagram.” As a programmer, you are in a
// better situation: you can have your computer do the work! Just write a little program ;-).
//
// The puzzle goes like this: Essentially, each row and column of a rectangular bitmap is annotated with the
// respective lengths of its distinct strings of occupied cells. The person who solves the puzzle must complete
// the bitmap given only these lengths.
//
// Problem statement:         Solution:
//
//|_|_|_|_|_|_|_|_| 3         |_|X|X|X|_|_|_|_| 3
//|_|_|_|_|_|_|_|_| 2 1       |X|X|_|X|_|_|_|_| 2 1
//|_|_|_|_|_|_|_|_| 3 2       |_|X|X|X|_|_|X|X| 3 2
//|_|_|_|_|_|_|_|_| 2 2       |_|_|X|X|_|_|X|X| 2 2
//|_|_|_|_|_|_|_|_| 6         |_|_|X|X|X|X|X|X| 6
//|_|_|_|_|_|_|_|_| 1 5       |X|_|X|X|X|X|X|_| 1 5
//|_|_|_|_|_|_|_|_| 6         |X|X|X|X|X|X|_|_| 6
//|_|_|_|_|_|_|_|_| 1         |_|_|_|_|X|_|_|_| 1
//|_|_|_|_|_|_|_|_| 2         |_|_|_|X|X|_|_|_| 2
// 1 3 1 7 5 3 4 3             1 3 1 7 5 3 4 3
// 2 1 5 1                     2 1 5 1
//
// For the example above, the problem can be stated as the two lists [[3],[2,1],[3,2],[2,2],[6],[1,5],[6],[1],[2]] 
// and [[1,2],[3,1],[1,5],[7,1],[5],[3],[4],[3]] which give the “solid” lengths of the rows and columns, top-to-bottom 
// and left-to-right, respectively. Published puzzles are larger than this example, e.g. 25×20, and apparently 
// always have unique solutions.
//
// might have a bug - not working - will look later.
//
object P98 {
  case class Nonogram(rows: List[List[Int]], cols: List[List[Int]])
  type Grid = Array[Array[Boolean]]

  def solveNonogram(nonogram: Nonogram): Option[Grid] = {
    val size = nonogram.rows.length
    val grid = Array.fill(size, size)(false)

    def isValidLine(line: Array[Boolean], constraints: List[Int]): Boolean = {
      val groups = line.mkString.split("false").map(_.count(_ == 't')).filter(_ > 0).toList
      groups == constraints
    }

    def isValid(grid: Grid, nonogram: Nonogram): Boolean = {
      (0 until size).forall { i =>
        isValidLine(grid(i), nonogram.rows(i)) && isValidLine(grid.map(_(i)), nonogram.cols(i))
      }
    }

    def solve(grid: Grid, row: Int, col: Int): Boolean = {
      if (row == size) return isValid(grid, nonogram)
      if (col == size) return solve(grid, row + 1, 0)

      grid(row)(col) = true
      if (isValid(grid, nonogram) && solve(grid, row, col + 1)) return true

      grid(row)(col) = false
      if (isValid(grid, nonogram) && solve(grid, row, col + 1)) return true

      false
    }

    if (solve(grid, 0, 0)) Some(grid) else None
  }

  def printGrid(grid: Grid): Unit = {
    grid.foreach { row =>
      println(row.map(if (_) "X" else "_").mkString(" "))
    }
  }
}

object P98Main extends App {
  import P98._

  val nonogram = Nonogram(
    rows = List(List(3), List(2, 1), List(3, 2), List(2, 2), List(6), List(1, 5), List(6), List(1), List(2)),
    cols = List(List(1, 2), List(3, 1), List(1, 5), List(7, 1), List(5), List(3), List(4), List(3))
  )

  solveNonogram(nonogram) match {
    case Some(grid) => printGrid(grid)
    case None => println("No solution exists")
  }
}