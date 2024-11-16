package com.github.diegopacheco.scala.playground2x.enums

import com.github.diegopacheco.scala.playground2x.enums.Fingers.{Finger, Little, Thumb}

class FingersOperation {
  def isShortest(finger: Finger) = finger == Little
}

object EnumsApp extends App{

  val thumbsUp = Thumb
  println(thumbsUp)

  val fingersOperation = new FingersOperation
  println(fingersOperation.isShortest(Little))
  println(fingersOperation.isShortest(Fingers.Index))

  val res = Fingers.values.toList.filter(finger => finger == Fingers.Middle || finger == Fingers.Index)
  println(res)

  // if we try to deserialize a non-existent value, we’ll get a java.util.NoSuchElementException.
  val deserialized = Fingers.withName("The Middle Finger")
  println(deserialized)



}
