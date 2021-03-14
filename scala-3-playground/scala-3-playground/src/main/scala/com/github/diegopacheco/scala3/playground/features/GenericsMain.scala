package com.github.diegopacheco.scala3.playground.features

object GenericsMain extends App{

  class Stack[A]:
    private var elements: List[A] = Nil
    def push(x: A):Unit = elements ::= x
    def peek:A = elements.head
    def pop():A =
      val currentTop = peek
      elements = elements.tail
      currentTop

  val stack = Stack[Int]()
  stack.push(1)
  stack.push(2)
  stack.push(3)
  println(s"pop = ${stack.pop()}")
  println(s"pop = ${stack.pop()}")
  println(s"pop = ${stack.pop()}")
  println(s"pop = ${stack.pop()}")
  
}
