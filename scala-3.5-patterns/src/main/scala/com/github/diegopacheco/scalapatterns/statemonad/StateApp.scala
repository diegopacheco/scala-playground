package com.github.diegopacheco.scalapatterns.statemonad

case class State[S, A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] = State { s =>
    val (a, newState) = run(s)
    (f(a), newState)
  }
  def flatMap[B](f: A => State[S, B]): State[S, B] = State { s =>
    val (a, newState) = run(s)
    f(a).run(newState)
  }
}

object State {
  def pure[S, A](a: A): State[S, A] = State(s => (a, s))

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))
  } yield ()
}

object StateApp extends App{

  def increment: State[Int, Unit] = State.modify(_ + 1)

  def program: State[Int, Int] = for {
    _ <- increment
    _ <- increment
    result <- State.get
  } yield result

  val initialState = 0
  val (result, finalState) = program.run(initialState)

  println(s"Result: $result") // Output: Result: 2
  println(s"Final State: $finalState") // Output: Final State: 2

}
