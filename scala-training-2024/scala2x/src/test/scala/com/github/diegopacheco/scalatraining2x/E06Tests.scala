package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._
import E06._

class E06Tests extends AnyFlatSpec with should.Matchers {

  "Tic tac toe game" should "be" in {
    val game = new TicTacToe
    game.play(1, 1) should be("X")
    game.play(1, 2) should be("O")
    game.play(2, 2) should be("X")
    game.play(2, 1) should be("O")
    game.play(3, 2) should be("X")
    game.play(3, 1) should be("O")
    game.play(3, 3) should be("Game over")
    val board = game.showBoard()
    println(board)
    board should be(
        "X | O |  \n" +
        "---------\n" +
        "O | X |  \n" +
        "---------\n" +
        "O | X | X\n" +
        "Player X won!"
    )
  }

}