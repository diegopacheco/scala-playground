package com.github.diegopacheco.scalatraining

/*
 * Implement a RPN calculator in Scala 3x
 */
object E03:
  def rpnCalculator(input: String): Int =
    val stack = scala.collection.mutable.Stack[Int]()
    input.split(" ").foreach {
      case "+" => stack.push(stack.pop() + stack.pop())
      case "-" => stack.push(-stack.pop() + stack.pop())
      case "*" => stack.push(stack.pop() * stack.pop())
      case "/" => stack.push((1 / stack.pop()) * stack.pop())
      case token => stack.push(token.toInt)
    }
    stack.pop()

object E03Main extends App:
  val result = E03.rpnCalculator("3 4 + 2 * 1 +")
  println(result)