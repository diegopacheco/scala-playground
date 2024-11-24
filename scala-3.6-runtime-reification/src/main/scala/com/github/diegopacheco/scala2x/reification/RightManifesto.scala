package com.github.diegopacheco.scala2x.reification

import com.eed3si9n.manifesto.Manifesto

object RightManifesto extends App:
  println(foo(List("hi"))) // xs is List[String]

  def foo[A1: Manifesto](xs: A1): String =
    val m = Manifesto[A1]
    val mli = Manifesto[List[Int]]
    val mls = Manifesto[List[String]]
    xs match
      case xs: List[Int] if m == mli => "xs is List[Int]"
      case xs: List[String] if m == mls => "xs is List[String]"
