package com.github.diegopacheco.scalatraining

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E01Tests extends AnyFlatSpec with should.Matchers:

  "Fizz Buzz from 1 to 100" should "be" in:
    import E01.*
    val result = (1 to 100).map(n => fizzBuzz(n)).mkString
    result should be("12Fizz4BuzzFizz78FizzBuzz11Fizz1314FizzBuzz1617Fizz19BuzzFizz2223" +
      "FizzBuzz26Fizz2829FizzBuzz3132Fizz34BuzzFizz3738FizzBuzz41Fizz4344FizzBuzz4647" +
      "Fizz49BuzzFizz5253FizzBuzz56Fizz5859FizzBuzz6162Fizz64BuzzFizz6768FizzBuzz71" +
      "Fizz7374FizzBuzz7677Fizz79BuzzFizz8283FizzBuzz86Fizz8889FizzBuzz9192Fizz94" +
      "BuzzFizz9798FizzBuzz")