package com.github.diegopacheco.scala.playground2x.obj

sealed trait CrustType
case object Thin extends CrustType
case object Rising extends CrustType
case object DeepDish extends CrustType

object CaseObjectsApp extends App{

  def addCrust(crust: CrustType): Unit = {
    crust match {
      case Thin => println("Thin")
      case Rising => println("Rising")
      case DeepDish => println("DeepDish")
    }
  }
  addCrust(Thin)

}
