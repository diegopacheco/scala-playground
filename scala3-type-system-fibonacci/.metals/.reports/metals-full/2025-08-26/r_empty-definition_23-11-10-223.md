error id: file://<WORKSPACE>/src/main/scala/Main.scala:scala/Predef.valueOf().
file://<WORKSPACE>/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: scala/Predef.valueOf().
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -valueOf.
	 -valueOf#
	 -valueOf().
	 -scala/Predef.valueOf.
	 -scala/Predef.valueOf#
	 -scala/Predef.valueOf().
offset: 173
uri: file://<WORKSPACE>/src/main/scala/Main.scala
text:
```scala
type Fibonacci[N <: Int] <: Int = N match
  case 0 => 0
  case 1 => 1
  case n => Fibonacci[n - 1] + Fibonacci[n - 2]

@main def main():Unit =
  val fibo3: Fibonacci[3] = va@@lueOf[Fibonacci[3]]
  println(fibo3)

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Predef.valueOf().