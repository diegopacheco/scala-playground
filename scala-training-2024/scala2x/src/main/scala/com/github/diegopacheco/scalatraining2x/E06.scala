package com.github.diegopacheco.scalatraining2x

/*
 * Implement a Tic Tac Toe game
 */
object E06 {

  abstract class Player(val symbol: Char)
  object Empty extends Player(symbol=' ')
  object PlayerX extends Player(symbol='X')
  object PlayerO extends Player(symbol='O')

  type Board = Array[Array[Player]]

  class TicTacToe {
    private val board: Board = Array.fill(3, 3)(Empty)
    private var currentPlayer: Player = PlayerX
    private var gameWon: Boolean = false
    private var gameDraw: Boolean = false

    private def isWin(player: Player): Boolean = {
      val rows = board.exists(row => row.forall(_ == player))
      val cols = board.transpose.exists(col => col.forall(_ == player))
      val diag1 = (0 until 3).forall(i => board(i)(i) == player)
      val diag2 = (0 until 3).forall(i => board(i)(2 - i) == player)
      rows || cols || diag1 || diag2
    }

    private def isDraw: Boolean = !board.flatten.contains(Empty)

    private def makeMove(row: Int, col: Int): Boolean = {
      if (board(row - 1)(col - 1) == Empty) {
        board(row - 1)(col - 1) = currentPlayer
        true
      } else {
        false
      }
    }

    private def switchPlayer(): Unit = {
      currentPlayer = if (currentPlayer == PlayerX) PlayerO else PlayerX
    }

    def play(row: Int, col: Int): String = {
      (gameWon, gameDraw, makeMove(row, col)) match {
        case (true, _, _) | (_, true, _) => "Game over"
        case (_, _, true) =>
          gameWon = isWin(currentPlayer)
          gameDraw = isDraw
          if (gameWon || gameDraw) {
            "Game over"
          } else {
            val player = currentPlayer
            switchPlayer()
            player.symbol.toString
          }
        case _ => "Invalid move"
      }
    }

    def showBoard(): String = {
      val boardString = board.map(_.map(_.symbol).mkString(" | ")).mkString("\n" + "-" * 9 + "\n")
      (gameWon, gameDraw) match {
        case (true, _) => s"$boardString\nPlayer ${currentPlayer.symbol} won!"
        case (_, true) => s"$boardString\nIt's a draw!"
        case _ => boardString
      }
    }
  }
}


object E06App extends App {
  val game = new E06.TicTacToe
  println(game.play(1, 1))
  println(game.showBoard())
  println(game.play(1, 2))
  println(game.showBoard())
  println(game.play(2, 1))
  println(game.showBoard())
  println(game.play(2, 2))
  println(game.showBoard())
  println(game.play(3, 1))
  println(game.showBoard())
}