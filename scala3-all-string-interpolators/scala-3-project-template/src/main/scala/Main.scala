@main def hello(): Unit =
  val height = 1.9d
  val name = "Dude"
  println(f"$name%s is $height%2.2f meters tall")  // "Dude is 1.90 meters tall"

  val str = "Scala 3"
  println(s"Best programing lang is ${str.toUpperCase()}")

  val year = raw"2024\n\rDragon!"
  println(year)

  customInterpolatorString()

case class Point(x: Double, y: Double)

extension (sc: StringContext)
  def p(args: Double*): Point = {
    // reuse the `s`-interpolator and then split on ','
    val pts = sc.s(args: _*).split(",", 2).map { _.toDoubleOption.getOrElse(0.0) }
    Point(pts(0), pts(1))
  }

def customInterpolatorString(): Unit =
  val x=12.0
  println(p"1, -2")        // Point(1.0, -2.0)
  println(p"${x/5}, $x")   // Point(2.4, 12.0)