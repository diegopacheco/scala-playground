package com.github.diegopacheco.bagofcats

import cats.syntax.either._ // for asRight

object CatMonadEither extends App{

  val a = 3.asRight[String]
  val b = 4.asRight[String]

  val res = for {
    x <- a
    y <- b
  } yield x*x + y*y

  println(res)

  def countPositive(nums: List[Int]) =
    nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
      if (num > 0) {
        accumulator.map(_ + 1)
      } else {
        Left("Negative. Stopping!")
      }
    }

  println(countPositive(List(1, 2, 3)))
  println(countPositive(List(1, -2, 3)))

  val res2 = Either.catchOnly[NumberFormatException]("foo".toInt)
  println(res2)

  val res3 = Either.catchNonFatal(sys.error("Badness"))
  println(res3)

  val res4 = Either.fromTry(scala.util.Try("foo".toInt))
  println(res4)

  val res5 = Either.fromOption[String, Int](None, "Badness")
  println(res5)

  val res6 = "Error".asLeft[Int].getOrElse(0)
  println(res6)

  val res7 = "Error".asLeft[Int].orElse(2.asRight[String])
  println(res7)

  val res8 = -1.asRight[String].ensure("Must be non-negative!")(_ > 0)
  println(res8)

  val res9 = "error".asLeft[Int].recover {
    case _: String => -1
  }
  println(res9)

  val res10 = "error".asLeft[Int].recoverWith {
    case _: String => Right(-1)
  }
  println(res10)

  val res11 = "foo".asLeft[Int].leftMap(_.reverse)
  println(res11)

  val res12 = 6.asRight[String].bimap(_.reverse, _ * 7)
  println(res12)

  val res13 = "bar".asLeft[Int].bimap(_.reverse, _ * 7)
  println(res13)

  println(123.asRight[String])
  println(123.asRight[String].swap)

  val res14 = for {
    a <- 1.asRight[String]
    b <- 0.asRight[String]
    c <- if(b == 0) "DIV0".asLeft[Int]
    else (a / b).asRight[String]
  } yield c * 100

  println(res14)

}
