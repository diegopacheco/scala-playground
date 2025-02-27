### Build

```
sbt compile
```

### Run

```
sbt run
```

### Rationale #1 Exceptions breaking out pattern matcher

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

### Rationale #2 Invalid Values on Either and Option

Let's say we consider this values invalid
```
null
Exception (but not throw)
```
It's possible to still have Some(null) or even Right(null) which is not what we want.

This string case goes Right(null)
```scala
  def process(): Either[Exception, String] = {
    Right(null)
  }
  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
```

This Option case goes Some(null)
```scala
  def process(): Option[String] = {
    Some(null)
  }
  process() match {
    case Some(res) => println(s"Some Success: $res")
    case None => println(s"None Error")
 }
```

This Exception case goes Right(Exception)
```scala
def process(): Either[Exception, Object] = {
    new RuntimeException("Error! Oopsy Dayzes.")
  }
  process() match {
    case Right(res) => println(s"Right Success: $res")
    case Left(ex) => println(s"Left Error: $ex")
  }
```

It would require some if or pattern matcher inside the provider method in order to avoid this issue.
So Strings with Either and Option can be problematic, good news is Int/Integer are safe.
Scala 3.x seems to take care of:
* Integer
* Int

Both coursed from null to 0.

### Rationale #3 Option is safe, Some is not, Either does not take parameter Right is not Safe

BAD
```scala
Some(value)
```

GOOD
```scala
Option(value)
```

1. You should never do Some(value) because value can be null.
2. So you would need an IF, pattern matcher or use Option(value) instead.
3. Option(value) does not hold exception - still does not work.
4. Either does not take parameter Right is not Safe.
5. I create a monad with a SafeEither like Try[Result] / Option(value) but better than option because it hold exceptions.
Look `ValidMonad` code.
6. PS: I tried to make SafeRight(invalid value) object but, it does not work because some circular reference that got the typesystem freakout and break.