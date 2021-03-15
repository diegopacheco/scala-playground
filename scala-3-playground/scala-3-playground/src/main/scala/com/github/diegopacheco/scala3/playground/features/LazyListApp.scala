package com.github.diegopacheco.scala3.playground.features

@main def LazyListApp():Unit = {
  val x = LazyList.range(1, Int.MaxValue)
  x.take(1)                    // LazyList(<not computed>)
  x.take(5)                    // LazyList(<not computed>)
  x.map(_ + 1)                 // LazyList(<not computed>)
  val result = x.takeWhile(_<10)
  println(result)              // LazyList(<not computed>)
  result.foreach(println _)
}
