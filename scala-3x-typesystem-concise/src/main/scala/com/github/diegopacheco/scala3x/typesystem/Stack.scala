package com.github.diegopacheco.scala3x.typesystem

class Stack[A]:
  private var elements: List[A] = Nil
  def push(x: A): Unit = elements ::= x
  def peek: A = elements.head
  def pop(): A =
    val currentTop = elements.head
    elements = elements.tail
    currentTop

object StackTypeSystemApp extends App:
  val stack = Stack[Int]()
  stack.push(1)
  stack.push(2)
  println(stack.pop())  // prints 2
  println(stack.pop())  // prints 1
