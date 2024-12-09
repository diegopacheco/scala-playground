package com.github.diegopacheco.snakegame

import scala.annotation.tailrec
import scala.concurrent.{Future, blocking}
import scala.concurrent.ExecutionContext.Implicits.global
import org.jline.terminal.{Terminal, TerminalBuilder}
import org.jline.keymap.KeyMap

enum Direction:
  case Up, Down, Left, Right

case class Position(x: Int, y: Int)
case class Snake(body: List[Position], direction: Direction)
case class GameState(snake: Snake, food: Position, width: Int, height: Int, score: Int)

object SnakeGame:
  val gameSpeed = 600
  @volatile private var latestInput: Option[String] = None

  def main(args: Array[String]): Unit =
    val initialState = GameState(
      Snake(List(Position(5, 5), Position(5, 4), Position(5, 3)), Direction.Right),
      Position(8, 8),
      20,
      20,
      0
    )
    startInputThread()
    gameLoop(initialState)

  private def startInputThread(): Unit =
    Future {
      val terminal: Terminal = TerminalBuilder.terminal()
      val keyMap: KeyMap[String] = new KeyMap()
      keyMap.bind("up", "w")
      keyMap.bind("down", "s")
      keyMap.bind("left", "a")
      keyMap.bind("right", "d")

      while (true) {
        blocking {
          val input = terminal.reader().read()
          val lastInput: Option[String] = Option(keyMap.getBound(input.toChar.toString))
          if (lastInput.isDefined){
            latestInput = lastInput
          }
          //println(s"Input received: $latestInput")
        }
      }
    }

  @tailrec
  private def gameLoop(state: GameState): Unit =
    printState(state)
    val newState = updateState(state, latestInput)
    Thread.sleep(gameSpeed)
    if (!isGameOver(newState)) gameLoop(newState)
    else println(s"Game Over! Your score: ${state.score}")

  private def printState(state: GameState): Unit =
    val grid = Array.fill(state.height, state.width)(' ')
    state.snake.body.foreach { case Position(x, y) => grid(y)(x) = 'O' }
    grid(state.food.y)(state.food.x) = 'X'
    val border = "+" + "-" * state.width + "+"
    println(border)
    grid.foreach(row => println("|" + row.mkString + "|"))
    println(border)
    println(s"Score: ${state.score}")

  private def updateState(state: GameState, input: Option[String]): GameState =
    val newDirection = input match
      case Some("up")    => if (state.snake.direction != Direction.Down) Direction.Up else state.snake.direction
      case Some("down")  => if (state.snake.direction != Direction.Up) Direction.Down else state.snake.direction
      case Some("left")  => if (state.snake.direction != Direction.Right) Direction.Left else state.snake.direction
      case Some("right") => if (state.snake.direction != Direction.Left) Direction.Right else state.snake.direction
      case _             => state.snake.direction

    println(s"New direction: $newDirection") // Debugging statement

    val newHead = move(state.snake.body.head, newDirection)
    val newBody = if (newHead == state.food) newHead :: state.snake.body else newHead :: state.snake.body.init
    val newFood = if (newHead == state.food) generateFood(state) else state.food
    val newScore = if (newHead == state.food) state.score + 1 else state.score

    state.copy(snake = Snake(newBody, newDirection), food = newFood, score = newScore)

  private def move(position: Position, direction: Direction): Position =
    direction match
      case Direction.Up    => position.copy(y = position.y - 1)
      case Direction.Down  => position.copy(y = position.y + 1)
      case Direction.Left  => position.copy(x = position.x - 1)
      case Direction.Right => position.copy(x = position.x + 1)

  @tailrec
  private def generateFood(state: GameState): Position =
    val newFood = Position(scala.util.Random.nextInt(state.width), scala.util.Random.nextInt(state.height))
    if (state.snake.body.contains(newFood)) generateFood(state) else newFood

  private def isGameOver(state: GameState): Boolean =
    val head = state.snake.body.head
    head.x < 0 || head.x >= state.width || head.y < 0 || head.y >= state.height ||
      state.snake.body.tail.contains(head)