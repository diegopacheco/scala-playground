package com.github.diegopacheco.scala.playground2x.traits

trait VideoGame
trait Snes extends VideoGame
trait N64 extends VideoGame
trait MegaDrive extends VideoGame

trait Camel
trait Bat
trait Glue
object Monster extends Camel with Bat with Glue

object TraitApp extends App{

  val snes = new Snes{}
  println(snes)

  val monster = Monster
  println(monster)

}
