package com.github.diegopacheco.scala.playground2x.implicits

object Logger {
  def info(msg: String)(implicit format:String): Unit = println(s"$format INFO: $msg")
}

trait Formatable[T] {
  def provide(): T
}

object Formatable {
  implicit val formatString = new Formatable[String] {
    override def provide(): String = "<<< "
  }
}

object Anotherlogger {
  def info[F: Formatable](msg: String): Unit = {
    val fmt = implicitly[Formatable[F]]
    println(s"${fmt.provide()} INFO: $msg")
  }
}

object ImplicitApp extends App{

  implicit val format = ">>> "
  Logger.info("Hello World")

  import Formatable._
  Anotherlogger.info("Hello World 2")

}
