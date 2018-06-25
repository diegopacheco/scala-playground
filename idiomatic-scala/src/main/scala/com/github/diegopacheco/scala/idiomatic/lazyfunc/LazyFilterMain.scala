package com.github.diegopacheco.scala.idiomatic.lazyfunc

object LazyFilterMain extends App {

  def infiniteList(n: Int): Stream[Int] = {
    n #:: infiniteList(n + 1)
  }

  def lazyFilter(f: Int => Boolean, n: Stream[Int]): Stream[Int] = {
    if (f(n.head)) {
      n.head #:: lazyFilter(f, n.tail)
    } else {
      lazyFilter(f, n.tail)
    }
  }

  lazyFilter(_ % 3 == 0, infiniteList(1)).take(10).foreach(println _)
  
}

