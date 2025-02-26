### Build

```
sbt compile
```

### Run

```
sbt run
```

### Rationale

The reason this POC exist is to proof correct and incorrect/risk way to handle error
handling in scala 3.x considering a functional code.

Prove that just using Either[Left,Right] does not guarantee to hold the exception.
Either works as:
```
Left => Error, Failure or Exception
Right => Success, Result or Value
```
This is important because such assumption might cost poor error handling or even swallowing exceptions.
```scala
 process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
 }
```
Why this matter? Because you need make sure no method is throwing exceptions. Remember that 
when we use libs specially in java, that might not be possible, we might need a custom wrapper to make it work.

The same problem will happen if you use Option[Some,None], Exceptions will not be hold.

Here we could mitigate this problem with a try/catch around maybeWorks() method.
```scala
  def process(): Either[Exception, String] = {
    try {
      maybeWorks()
    } catch {
      case e: Exception => Left(e)
    }
  }
```

Another way to fix this is using a Try[Result] instead of Either[Left,Right] or Option[Some,None].
```scala
  def process(): Either[Exception,Try[Int]] = {
    val res = Try(maybeWorks())
    Right(res)
  }
  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
```
Try guarantees exception will not be thrown.
```
Right Success: Failure(java.lang.RuntimeException: Error! Oopsy Dayzes.)
```

### Result

As you can see Either[Left,Right] does not guarantee to HOLD the exception.
Whatever method you call you need make sure it does not throw exception.

```
Exception in thread "main" java.lang.RuntimeException: Error! Oopsy Dayzes.
	at Main$package$.maybeWorks(Main.scala:4)
	at Main$package$.process(Main.scala:10)
	at Main$package$.main(Main.scala:25)
	at main.main(Main.scala:24)
```


