package com.github.diegopacheco.scalaplayground

object TypeLevelProgramming {
  import scala.compiletime.ops.int.*

  type TupleLength[T <: Tuple] <: Int = T match
    case EmptyTuple => 0
    case _ *: tail => 1 + TupleLength[tail]

  def tupleLength[T <: Tuple](using tl: ValueOf[TupleLength[T]]): Int =
    tl.value
}

object TypeLevelApp extends App{
  import TypeLevelProgramming.*

  private val tuple0 = (EmptyTuple)
  private val tuple1 = (1, "hello", true)
  private val tuple2 = (42, "Scala", 3.14, 'c')

  println(s"Length of tuple0: ${tupleLength[tuple0.type]}")
  println(s"Length of tuple1: ${tupleLength[tuple1.type]}")
  println(s"Length of tuple2: ${tupleLength[tuple2.type]}")
}
