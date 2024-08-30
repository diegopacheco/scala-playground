
@main def main(): Unit = {

  import scala.language.experimental.modularity

  trait Ord:
    type Self
    extension (x: Self)
      def compareTo(y: Self): Int
      def <(y: Self): Boolean = compareTo(y) < 0
      def >(y: Self): Boolean = compareTo(y) > 0

  given intOrd: (Int is Ord) with
    extension (x: Int) def compareTo(y: Int) = if x < y then -1 else if x > y then +1 else 0

  def descending[T: Ord as asc]: T is Ord = new:
    extension (x: T) def compareTo(y: T) = asc.compareTo(y)(x)

  def maximum[T](xs: List[T])(using T is Ord): T =
    xs.reduceLeft((x, y) => if (x < y) y else x)

  val xs = List(1, 2, 3)
  val a = maximum(xs)
  val b = maximum(xs)(using descending)
  val c = maximum(xs)(using descending(using intOrd))

  println(xs)
  println(s"maximum(xs) = ${a} ")
  println(s"maximum(xs)(using descending) = ${b} ")
  println(s"maximum(xs)(using descending(using intOrd)) = ${c} ")

}