package com.github.diegopacheco.scala3x.playground

val answer = 42

def question: String =
  "What is the answer to the Ultimate Question of Life, the Universe, and Everything?"

case class User(name: String, age: Int)

@main def main =
  val johnDoe = User("John Doe", 50)
  println(s"${johnDoe.name} is ${johnDoe.age} years old")
  println(question)
  println(answer)