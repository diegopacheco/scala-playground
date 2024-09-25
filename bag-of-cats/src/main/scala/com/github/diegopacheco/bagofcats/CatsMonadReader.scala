package com.github.diegopacheco.bagofcats

import cats.data.Reader

/**
 * Reader Monad
 *   is a Monad that allows you to sequence operations that depend on some input.
 *
 * Example use cases:
 *  - Configuration
 *  - Dependency Injection
 *  - Environment
 *  - Context
 *  - State
 */
object CatsMonadReader extends App{

  final case class Cat(name: String, favoriteFood: String)

  val catName: Reader[Cat, String] = Reader(cat => cat.name)
  val res1 = catName.run(Cat("Garfield", "lasagne"))
  println(s"catName $catName - res1: ${res1} ")

  val greetKitty: Reader[Cat, String] =
    catName.map(name => s"Hello ${name}")
  println(greetKitty.run(Cat("Heathcliff", "junk food")))

  val feedKitty: Reader[Cat, String] =
    Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

  val greetAndFeed: Reader[Cat, String] =
    for {
      greet <- greetKitty
      feed  <- feedKitty
    } yield s"$greet. $feed."
  println(greetAndFeed(Cat("Garfield", "lasagne")))

}
