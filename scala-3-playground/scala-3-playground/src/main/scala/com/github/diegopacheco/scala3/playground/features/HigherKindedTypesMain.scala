package com.github.diegopacheco.scala3.playground.features

@main def HigherKindedTypesMain():Unit = {

  trait Collection[T[_]] {
    def wrap[A](a: A): T[A]
    def first[B](b: T[B]): B
  }

  var collection = new Collection[List] {
    override def wrap[A](a: A): List[A] = List(a)
    override def first[B](b: List[B]): B = b.head
  }
  
  val result = collection.wrap("Some values")
  val first = collection.first(List("Some values"))

  println(s"wrap  == ${result}") 
  println(s"first == ${first}")
  
}
