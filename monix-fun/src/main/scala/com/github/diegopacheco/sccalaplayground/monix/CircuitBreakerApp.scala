package com.github.diegopacheco.sccalaplayground.monix

object CircuitBreakerApp extends App {

  import monix.catnap.CircuitBreaker
  import monix.eval._
  import scala.concurrent.duration._
  implicit val scheduler = monix.execution.Scheduler.global

  val circuitBreaker = CircuitBreaker[Task].of(
    maxFailures = 5,
    resetTimeout = 10.seconds,

    onRejected = Task {
      println("Task rejected in Open or HalfOpen")
    },
    onClosed = Task {
      println("Switched to Close, accepting tasks again")
    },
    onHalfOpen = Task {
      println("Switched to HalfOpen, accepted one task for testing")
    },
    onOpen = Task {
      println("Switched to Open, all incoming tasks rejected for the next 10 seconds")
    }
  )

  val problematic = Task {
    val nr = util.Random.nextInt()
    if (nr % 2 == 0) nr else
      throw new RuntimeException("dummy")
  }

  var result = for {
    ci <- circuitBreaker
    r  <- ci.protect(problematic)
  } yield r

  result.runToFuture.foreach(println)
  Thread.sleep(5000L)
}
