package com.github.diegopacheco.bagofcats


/**
 * Monads are a design pattern that allows you to chain operations together.
 * Moands need to have 2 operations: flatMap and unit.
 */
object Monads extends App {

  def parseInt(str: String): Option[Int] =
    scala.util.Try(str.toInt).toOption

  def divide(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a / b)

  def stringDivideBy(aStr: String, bStr: String): Option[Int] =
    parseInt(aStr).flatMap { aNum =>
      parseInt(bStr).flatMap { bNum =>
        divide(aNum, bNum)
      }
    }

  println(stringDivideBy("6", "2")) // Some(3)
  println(stringDivideBy("6", "boo")) // None


  /**
   * Using for comprehension to clarify the behavior.
   */
  def stringDivideBy2(aStr: String, bStr: String): Option[Int] =
    for {
      aNum <- parseInt(aStr)
      bNum <- parseInt(bStr)
      ans <- divide(aNum, bNum)
    } yield ans

  println(stringDivideBy2("6", "2")) // Some(3)
  println(stringDivideBy2("6", "boo")) // None

  val pairs = for {
    x <- (1 to 3).toList
    y <- (4 to 5).toList
  } yield (x, y)
 pairs.foreach { case (x, y) => println(s"$x $y") }


}
