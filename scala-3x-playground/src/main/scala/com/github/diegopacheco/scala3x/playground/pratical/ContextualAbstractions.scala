package com.github.diegopacheco.scala3x.playground.pratical

import java.util.Date

trait Logger:
  def log(message: String): Unit

class ConsoleLogger extends Logger:
  def log(message: String): Unit = println(s"LOG: $message")

//
// Contextual Abstractions (Given and Using)
// Contextual abstractions simplify dependency injection and type class usage.
//
object Loggers {
  given std: Logger = new ConsoleLogger
  given date: Logger = new Logger:
    override def log(message: String): Unit = println(s"${new Date()} - LOG: $message")
}

object ContextualAbstractions extends App:
  class UserService(using logger: Logger):
    def createUser(name: String): Unit =
      logger.log(s"Creating user: $name")
      println(s"User $name created")

  import Loggers.std
  val userService = UserService()
  userService.createUser("Alice")

  def newContext : Unit =
    import Loggers.date
    val userService2 = UserService()
    userService2.createUser("Bob")

  newContext