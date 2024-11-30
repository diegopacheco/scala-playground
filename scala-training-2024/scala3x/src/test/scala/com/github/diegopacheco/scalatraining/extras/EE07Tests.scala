package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE07Tests extends AnyFlatSpec with should.Matchers:
  "Test the Mars Rover" should "work" in {
    import EE07.*
    import EE07.Direction.*
    val initialPosition = Position(0, 0, N)
    val obstacles = Set((1, 1), (2, 2))
    val map = new Mapping(5, 5, obstacles)
    val rover = new Rover(initialPosition, map)
    val commands = "⬆➡⬆⬅⬆"
    rover.executeCommands(commands)
    rover.position should be(Position(0, 2, N))
  }
