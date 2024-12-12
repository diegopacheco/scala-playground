## Rationally

It automatically convert from Option to Optional
and vice versa. Just need a import `import ConversionsOps.given`

JavaLike.scala
```scala
import java.util.Optional

object JavaLike {
  def addOne(optional: Optional[Int]): Optional[Int] = {
    if (optional.isPresent) {
      Optional.of(optional.get + 1)
    } else {
      Optional.empty()
    }
  }
}
```

MainApp.scala
```scala
object MainApp extends App{
  import ConversionsOps.given

  val opt: Option[Int] = Some(41)
  private val result: Option[Int] = JavaLike.addOne(opt)
  println(result)
}
```

### Build
```
sbt compile
```

### Run
```
sbt run
```

