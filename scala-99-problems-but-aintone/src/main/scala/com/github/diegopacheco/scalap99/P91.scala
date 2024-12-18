package com.github.diegopacheco.scalap99

// P91 (**) Knight's tour.
//     Another famous problem is this one: How can a knight jump on an NÃ—N
//     chessboard in such a way that it visits every square exactly once?
//
//     Hints: Represent the squares by pairs of their coordinates of the form
//     (X, Y), where both X and Y are integers between 1 and N. (Alternately,
//     define a Point class for the same purpose.)  Write a function
//     jump(N, (X, Y), (U, V)) to express the fact that a knight can jump from
//     (X, Y) to (U, V) on a NÃ—N chessboard.  And finally, represent the
//     solution of our problem as a list of knight positions (the knight's
//     tour).
object P91 {
  case class Point(x: Int, y: Int) {
    def +(o: Point) = Point(x + o.x, y + o.y)
    def -(o: Point) = Point(x - o.x, y - o.y)
    def +(t: (Int, Int)) = Point(x + t._1, y + t._2)
    def -(t: (Int, Int)) = Point(x - t._1, y - t._2)
    def jumps(n: Int): List[Point] =
      List((2, 1), (1, 2), (-1, 2), (-2, 1)) flatMap { x =>
        List(this + x, this - x)
      } filter { p =>
        (p.x min p.y) >= 1 && (p.x max p.y) <= n
      }
  }

  import scala.collection.mutable.ListBuffer

  def filtered(p: Point, n: Int, s: Set[Point]): ListBuffer[Point] = {
    val jumps = ListBuffer(p.jumps(n): _*)
    jumps --= s
    jumps
  }

  def jumps(p: Point, n: Int, s: Set[Point]): List[Point] = {
    filtered(p, n, s).sortBy(filtered(_, n, s).length).toList
  }

  class TourFound(val tour: List[Point]) extends Exception

  def knightsTour(n: Int): List[Point] = knightsTour(n, Point(1, 1), (p, s) => s.size == n * n)

  def knightsTourClosed(n: Int): List[Point] =
    knightsTour(n, Point(1, 1), (p, s) => s.size == n * n && p.jumps(n).contains(Point(1, 1)))

  def knightsTour(n: Int, start: Point, done: (Point, Set[Point]) => Boolean): List[Point] =
    try {
      findPath(n, start, Set(start), List(start), done)
      Nil
    } catch {
      case t: TourFound => t.tour
    }

  def findPath(n: Int, p: Point, s: Set[Point], soFar: List[Point], done: (Point, Set[Point]) => Boolean): Unit = {
    if (done(p, s)) throw new TourFound(soFar)
    else jumps(p, n, s).foreach { q =>
      findPath(n, q, s + q, q :: soFar, done)
    }
  }

  def findAllPaths(n: Int, p: Point, s: Set[Point], soFar: List[Point]): List[List[Point]] =
    if (s.size == n * n) List(soFar)
    else jumps(p, n, s).flatMap(q => findAllPaths(n, q, s + q, q :: soFar))

  def knightsTourComplete(n: Int): List[List[Point]] = knightsTourComplete(n, Point(1, 1))

  def knightsTourCompleteClosed(n: Int): List[List[Point]] =
    knightsTourComplete(n, Point(1, 1)).filter(_.head.jumps(n).contains(Point(1, 1)))

  def knightsTourComplete(n: Int, start: Point): List[List[Point]] =
    findAllPaths(n, start, Set(start), List(start))

  case class Frame(n: Int, p: Point, s: Set[Point], soFar: List[Point])

  def knightsTourLazy(n: Int): LazyList[List[Point]] = knightsTourLazy(n, Point(1, 1))

  def knightsTourLazyClosed(n: Int): LazyList[List[Point]] =
    knightsTourLazy(n, Point(1, 1)).filter(_.head.jumps(n).contains(Point(1, 1)))

  def knightsTourLazy(n: Int, start: Point): LazyList[List[Point]] =
    nextTour(List(Frame(n, start, Set(start), List(start))))

  def nextTour(stack: List[Frame]): LazyList[List[Point]] = stack match {
    case Nil => LazyList.empty
    case Frame(n, p, s, soFar) :: tail =>
      if (s.size == n * n) LazyList.cons(soFar, nextTour(tail))
      else nextTour(jumps(p, n, s).map(q => Frame(n, q, s + q, q :: soFar)).toList ::: tail)
  }
}

object P91Main extends App {
  import P91._

  println(Point(1, 1).jumps(8))
  println(Point(8, 8).jumps(8))

  println(knightsTour(8))
  println(knightsTourClosed(8))

  println(knightsTourComplete(5).length)
  println(knightsTourCompleteClosed(5).length)

  println(knightsTourLazy(5).take(1).toList)
  println(knightsTourLazyClosed(5).take(1).toList)
}