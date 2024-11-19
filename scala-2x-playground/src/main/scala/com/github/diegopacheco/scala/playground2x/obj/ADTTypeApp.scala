package com.github.diegopacheco.scala.playground2x.obj

trait Repeat {
  type RepeatType

  def apply(item: RepeatType, reps: Int): RepeatType
}

object RepeatString extends Repeat {
  type RepeatType = String
  def apply(item: RepeatType, reps: Int): RepeatType = item * reps
}

object RepeatInt extends Repeat {
  type RepeatType = Int
  def apply(item: RepeatType, reps: Int): RepeatType = item * reps
}

object ADTTypeApp extends App{
  println(RepeatString("NAN", 3))
  println(RepeatInt(3, 3))
}
