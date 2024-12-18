package com.github.diegopacheco.scalap99

// P97 (**) Sudoku.
//
// Sudoku puzzles go like this:
// Problem statement                Solution
//
//  . . 4 | 8  . .  | . 1  7        9  3  4 | 8  2  5 | 6  1  7
//        |         |                      |         |
//6  7  . | 9  . .  | . . .        6  7  2 | 9  1  4 | 8  5  3
//        |         |                      |         |
// 5  . 8 | . 3   . | . . 4        5  1  8 | 6  3  7 | 9  2  4
//--------+---------+--------      --------+---------+--------
// 3  . . | 7  4  . | 1  . .       3  2  5 | 7  4  8 | 1  6  9
//        |         |                      |         |
// . 6  9 |   . . . | 7  8  .      4  6  9 | 1  5  3 | 7  8  2
//        |         |                      |         |
//  . . 1 | . 6  9  | . . 5        7  8  1 | 2  6  9 | 4  3  5
//--------+---------+--------      --------+---------+--------
// 1  . . | . 8  .  | 3  . 6       1  9  7 | 5  8  2 | 3  4  6
//        |         |                      |         |
//  . . . | . . 6   | . 9  1         8  5  3 | 4  7  6 | 2  9  1
//        |         |                      |         |
//2  4  . | . . 1   | 5  . .       2  4  6 | 3  9  1 | 5  7  8
//
// Every spot in the puzzle belongs to a (horizontal) row and a (vertical) column, as well as to one single 3×3 square (which we call “square” for short).  At the beginning, some of the spots carry a single-digit number between 1 and 9.  The problem is to fill the missing spots with digits in such a way that every number between 1 and 9 appears exactly once in each row, in each column, and in each square.
//
object P97 {
  type Board = Array[Array[Int]]

  def solveSudoku(board: Board): Boolean = {
    def isValid(board: Board, row: Int, col: Int, num: Int): Boolean = {
      for (i <- 0 until 9) {
        if (board(row)(i) == num || board(i)(col) == num ||
          board(row / 3 * 3 + i / 3)(col / 3 * 3 + i % 3) == num) {
          return false
        }
      }
      true
    }

    def solve(board: Board): Boolean = {
      for (row <- 0 until 9; col <- 0 until 9 if board(row)(col) == 0) {
        for (num <- 1 to 9) {
          if (isValid(board, row, col, num)) {
            board(row)(col) = num
            if (solve(board)) return true
            board(row)(col) = 0
          }
        }
        return false
      }
      true
    }
    solve(board)
  }

  def printBoard(board: Board): Unit = {
    for (row <- board) {
      println(row.map(num => if (num == 0) "." else num.toString).mkString(" "))
    }
  }
}

object P97Main extends App {
  import P97._

  val board: Board = Array(
    Array(0, 0, 4, 8, 0, 0, 0, 1, 7),
    Array(6, 7, 0, 9, 0, 0, 0, 0, 0),
    Array(5, 0, 8, 0, 3, 0, 0, 0, 4),
    Array(3, 0, 0, 7, 4, 0, 1, 0, 0),
    Array(0, 6, 9, 0, 0, 0, 7, 8, 0),
    Array(0, 0, 1, 0, 6, 9, 0, 0, 5),
    Array(1, 0, 0, 0, 8, 0, 3, 0, 6),
    Array(0, 0, 0, 0, 0, 6, 0, 9, 1),
    Array(2, 4, 0, 0, 0, 1, 5, 0, 0)
  )

  if (solveSudoku(board)) {
    printBoard(board)
  } else {
    println("No solution exists")
  }
}