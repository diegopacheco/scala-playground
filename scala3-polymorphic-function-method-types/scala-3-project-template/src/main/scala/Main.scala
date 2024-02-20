@main def hello(): Unit =

  // polymorphic method
  def rev[A](xs: List[A]): List[A] = xs.reverse

  // polymorphic value
  val aList: [A] => List[A] => List[A]
      = [A] => (xs: List[A]) => rev[A](xs)

  println(rev(List(1,2,3)))
  println(aList)
