package com.github.diegopacheco.scalaplayground.kyo

import kyo.*

object KyoSimpleApp extends KyoApp{
  // Use 'run' blocks to execute Kyo computations.
  // The execution of the run block is lazy to avoid
  // field initialization issues.
  run {
    for
      _            <- Console.println(s"Main args: ${args.mkString("Array(", ", ", ")")}")
      currentTime  <- Clock.now
      _            <- Console.println(s"Current time is: $currentTime")
      randomNumber <- Random.nextInt(100)
      _            <- Console.println(s"Generated random number: $randomNumber")
    yield
      // The produced value can be of any type and is
      // automatically printed to the console.
      "example"
  }
}
