package com.github.diegopacheco.sccalaplayground.monix

object MVarApp extends App {

  implicit val scheduler = monix.execution.Scheduler.global
  import monix.catnap.MVar
  import monix.eval.Task

  def sum(state: MVar[Task, Int], list: List[Int]): Task[Int] =
    list match {
      case Nil => state.take
      case x :: xs =>
        state.take.flatMap { current =>
          state.put(current + x).flatMap(_ => sum(state, xs))
        }
    }

  val task =
    for {
      state <- MVar[Task].of(0)
      r <- sum(state, (0 until 100).toList)
    } yield r

  // Evaluate
  task.runToFuture.foreach(println)

}
