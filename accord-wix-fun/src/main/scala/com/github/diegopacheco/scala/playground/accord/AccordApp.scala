package com.github.diegopacheco.scala.playground.accord

import com.wix.accord.validate
import com.wix.accord.dsl._

object AccordApp extends App {

  import com.wix.accord.Validator

  trait Person {
    def name: String
    def surname: String
    def age: Int
  }
  object Person {
    val personValidator: Validator[Person] =
      validator[Person] { person:Person =>
        person.name is notEmpty
        person.surname is notEmpty
        person.age is between( 0, 120 )
      }
  }

  case class Adult( name:String, surname: String, age:Int, contactInfo:String ) extends Person
  case object Adult {
    implicit val adultValidator: Validator[ Adult ] =
      validator[ Adult ] { adult =>
        adult is valid( Person.personValidator )
        adult.age should be >= 18
        adult.contactInfo is notEmpty
      }
  }

  val validAdult = Adult( name = "Grace", surname = "Hopper", age = 85, contactInfo = "Arlington National Cemetery" )
  val result = validate(validAdult)

  val notValidAdult = Adult( name = "", surname = "Hopper", age = 85, contactInfo = "Arlington National Cemetery" )
  val notResult = validate(notValidAdult)

  println(s"Adult : ${validAdult} is ${result}")
  println(s"Adult : ${notValidAdult} is ${notResult}")

}
