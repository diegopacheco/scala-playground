package com.github.diegopacheco.snakegame


import scala.annotation.tailrec
import scala.concurrent.{Future, blocking}
import scala.concurrent.ExecutionContext.Implicits.global
import org.jline.terminal.{Terminal, TerminalBuilder}
import org.jline.reader.{LineReader, LineReaderBuilder}
import org.jline.keymap.KeyMap

enum Direction:
  case Up, Down, Left, Right

case class Position(x: Int, y: Int)

case class Snake(body: List[Position], direction: Direction)

case class GameState(snake: Snake, food: Position, width: Int, height: Int, score: Int)

object SnakeGame:
  @volatile private var latestInput: String = ""

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
      val reader: LineReader = LineReaderBuilder.builder().terminal(terminal).build()
      val keyMap: KeyMap[String] = new KeyMap()
      keyMap.bind("up", "\u001B[A") // Arrow up
      keyMap.bind("down", "\u001B[B") // Arrow down
      keyMap.bind("left", "\u001B[D") // Arrow left
      keyMap.bind("right", "\u001B[C") // Arrow right
      keyMap.bind("up", "w")
      keyMap.bind("down", "s")
      keyMap.bind("left", "a")
      keyMap.bind("right", "d")

      while (true) {
        blocking {
          val input = reader.readCharacter(keyMap)
          latestInput = input.toString
        }
      }
    }

  @tailrec
  def gameLoop(state: GameState): Unit =
    printState(state)
    val newState = updateState(state, latestInput)
    Thread.sleep(500) // Adjust the speed of the game
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

  private def updateState(state: GameState, input: String): GameState =
    val newDirection = input match
      case "up" => Direction.Up
      case "down" => Direction.Down
      case "left" => Direction.Left
      case "right" => Direction.Right
      case _ => state.snake.direction

    val newHead = move(state.snake.body.head, newDirection)
    val newBody = if (newHead == state.food) newHead :: state.snake.body else newHead :: state.snake.body.init
    val newFood = if (newHead == state.food) generateFood(state) else state.food
    val newScore = if (newHead == state.food) state.score + 1 else state.score

    state.copy(snake = Snake(newBody, newDirection), food = newFood, score = newScore)

  private def move(position: Position, direction: Direction): Position =
    direction match
      case Direction.Up => position.copy(y = position.y - 1)
      case Direction.Down => position.copy(y = position.y + 1)
      case Direction.Left => position.copy(x = position.x - 1)
      case Direction.Right => position.copy(x = position.x + 1)

  @tailrec
  def generateFood(state: GameState): Position =
    val newFood = Position(scala.util.Random.nextInt(state.width), scala.util.Random.nextInt(state.height))
    if (state.snake.body.contains(newFood)) generateFood(state) else newFood

  private def isGameOver(state: GameState): Boolean =
    val head = state.snake.body.head
    head.x < 0 || head.x >= state.width || head.y < 0 || head.y >= state.height || state.snake.body.tail.contains(head)