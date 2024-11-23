package com.github.scalaplayground.lib

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GreetingSpec extends AnyFlatSpec with Matchers {
  "Greeting" should "return a greeting message" in {
    Greeting.sayHello shouldEqual "Hello Scala 2.13.15 and Bazel!"
  }
}