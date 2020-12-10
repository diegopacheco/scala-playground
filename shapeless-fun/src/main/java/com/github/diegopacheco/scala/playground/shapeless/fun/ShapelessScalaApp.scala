package com.github.diegopacheco.scala.playground.shapeless.fun

object ShapelessScalaApp extends App {

  println("records.............. ")

  import shapeless._
  import record._
  import ops.hlist.ToList
  import ops.record.{ Keys, Values }
  import syntax.singleton._

  def printBook[B <: HList, K <: HList, V <: HList](b : B)
                                                   (implicit
                                                    keys: Keys.Aux[B, K],
                                                    values: Values.Aux[B, V],
                                                    ktl: ToList[K, Any],
                                                    vtl: ToList[V, Any]) = {
    (b.keys.toList zip b.values.toList) foreach { case (field, value) => println(s"$field: $value") }
    println()
  }

  val book =
    ("author" ->> "Benjamin Pierce") ::
      ("title"  ->> "Types and Programming Languages") ::
      ("id"     ->>  262162091) ::
      ("price"  ->>  44.11) ::
      HNil

  printBook(book)

  // Read price field
  val currentPrice = book("price")  // Static type is Double
  println("Current price is "+currentPrice)
  println()

  // Update price field, relying on static type of currentPrice
  val updated = book + ("price" ->> (currentPrice+2.0))
  printBook(updated)

  // Add a new field
  val extended = updated + ("inPrint" ->> true)
  printBook(extended)

  // Remove a field
  val noId = extended - "id"
  printBook(noId)


  println("klist.............. ")

  import shapeless._
  import UnaryTCConstraint._

  // Function which will only accept HList's whose elements all have Option as their
  // outer type constructor
  def acceptOption[L <: HList : *->*[Option]#λ](l : L) = true

  val l1 = Option(23) :: Option(true) :: Option("foo") :: HNil
  val l2 = Option(23) :: true :: Option("foo") :: HNil

  acceptOption(l1)

}
