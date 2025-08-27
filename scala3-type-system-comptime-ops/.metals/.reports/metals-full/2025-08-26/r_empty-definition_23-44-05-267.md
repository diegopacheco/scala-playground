error id: file://<WORKSPACE>/src/main/scala/Main.scala:scala/Predef.valueOf().
file://<WORKSPACE>/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: scala/Predef.valueOf().
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -HelloReversed.
	 -HelloReversed#
	 -HelloReversed().
	 -scala/Predef.HelloReversed.
	 -scala/Predef.HelloReversed#
	 -scala/Predef.HelloReversed().
offset: 658
uri: file://<WORKSPACE>/src/main/scala/Main.scala
text:
```scala
@main def main(): Unit =

  type Concat[A <: Tuple, B <: Tuple] <: Tuple = A match
    case EmptyTuple => B
    case h *: t => h *: Concat[t, B]

  type Reverse[T <: Tuple] <: Tuple = T match
    case EmptyTuple => EmptyTuple
    case h *: t => Concat[Reverse[t], h *: EmptyTuple]

  type Hello = ("H", "e", "l", "l", "o")
  type HelloReversed = Reverse[Hello]

  given helloReversedInstance: HelloReversed = ("o", "l", "l", "e", "H")

  summon[HelloReversed =:= ("o", "l", "l", "e", "H")]
  println("Type-level tuple reverse compiled successfully.")

  val rev = summon[HelloReversed]
  println("HelloReversed type alias computes to: " + rev)
  println(Hell@@oReversed[HelloReversed])
```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Predef.valueOf().