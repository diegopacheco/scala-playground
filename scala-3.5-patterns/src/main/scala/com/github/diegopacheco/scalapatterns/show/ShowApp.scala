package com.github.diegopacheco.scalapatterns.show

// Type Class
trait Show[A] {
  def show(a: A): String
}

object Show {
  implicit val intShow: Show[Int] = (a: Int) => a.toString
  implicit val stringShow: Show[String] = (a: String) => a
}

def show[A](a: A)(implicit s: Show[A]): String = s.show(a)

object ShowApp extends App{
  println(show(1))
  println(show("Hello"))
}
