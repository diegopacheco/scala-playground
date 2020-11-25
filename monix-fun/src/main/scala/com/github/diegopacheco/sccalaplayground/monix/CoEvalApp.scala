package com.github.diegopacheco.sccalaplayground.monix

import monix.eval.Coeval

import scala.io.StdIn
import scala.util.control.NonFatal

object CoEvalApp extends App {

  val prompt: Coeval[Unit] = Coeval.eval(println("Please enter a number"))
  val lastPrompt: Coeval[Unit] = Coeval.eval(println(s"Please enter a number, otherwise 42 will be used"))
  val readLine: Coeval[String] = Coeval.eval(StdIn.readLine())

  def promptForNumber(prompt: Coeval[Unit]): Coeval[Int] =
    for {
      _ <- prompt
      s <- readLine
    } yield s.toInt

  val num = promptForNumber(prompt)
    .onErrorRestart(2)
    .onErrorFallbackTo(promptForNumber(lastPrompt))
    .onErrorRecover {
      case NonFatal(_) => 42
    }.memoize // Saves result after first execution

  println(s"${num.value}") // First call, with side effects and actual computations
  println(s"${num.value}") // Returns memoized result

}
