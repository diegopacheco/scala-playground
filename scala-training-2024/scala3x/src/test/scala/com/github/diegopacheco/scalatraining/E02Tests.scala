package com.github.diegopacheco.scalatraining

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E02Tests extends AnyFlatSpec with should.Matchers:

  "hello is not a palindrome" should "return false" in:
    import E02.*
    isPalindrome("hello") should be(false)

  "helloolleh is a palindrome" should "return true" in:
    import E02.*
    isPalindrome("helloolleh") should be(true)