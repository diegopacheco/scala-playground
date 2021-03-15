package com.github.diegopacheco.scala3.playground.features

@main def MyMapMain():Unit = {
  
  def mymap[A](f: (Int) => A, xs: List[Int]): List[A] =
    for x <- xs yield f(x)
  
  val ints = List(1,2,3,4,5,6,7,8,9,10);
  val result = mymap(_*2,ints);
  println(s"My Map in action == ${result}")

  def mapGeneric[A,B](f: (B) => A, xs: List[B]): List[A] =
    for x <- xs yield f(x)
  
  val result2 = mapGeneric[Any,Int](_ + 1,ints);
  println(s"My Map Generic in action == ${result2}")
  
} 