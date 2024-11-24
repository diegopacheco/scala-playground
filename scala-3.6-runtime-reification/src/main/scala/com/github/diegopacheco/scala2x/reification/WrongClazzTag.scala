package com.github.diegopacheco.scala2x.reification

import scala.reflect.ClassTag

object WrongClazzTag extends App:
  println(describeType(List("hi"))) // xs is List[Int]

  def describeType[A1: ClassTag](xs: A1): String =
    val m = summon[ClassTag[A1]]
    val mli = summon[ClassTag[List[Int]]]
    val mls = summon[ClassTag[List[String]]]
    xs match
      case xs: List[Int] if m == mli => "xs is List[Int]"
      case xs: List[String] if m == mls => "xs is List[String]"
