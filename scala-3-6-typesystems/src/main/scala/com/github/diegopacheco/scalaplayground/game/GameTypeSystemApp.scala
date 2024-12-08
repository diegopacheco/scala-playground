package com.github.diegopacheco.scalaplayground.game

sealed trait Move
case object Rock extends Move
case object Paper extends Move
case object Scissors extends Move

sealed trait GameResult
case object Win extends GameResult
case object Lose extends GameResult
case object Draw extends GameResult

object Game {
  def play(p1: Move, p2: Move): GameResult = (p1, p2) match {
    case (Rock, Scissors) | (Scissors, Paper) | (Paper, Rock) => Win
    case (Scissors, Rock) | (Paper, Scissors) | (Rock, Paper) => Lose
    case _ => Draw
  }
}

object GameTypeSystemApp extends App {
  println("Rock vs Scissors: " + Game.play(Rock, Scissors))
  println("Rock vs Paper: " + Game.play(Rock, Paper))
  println("Rock vs Rock: " + Game.play(Rock, Rock))
}