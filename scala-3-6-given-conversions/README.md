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

we can also do with scala <-> java

ReallyJava.java
```java
import java.util.Optional;

public class ReallyJava {
    public Optional<Integer> addTwo(Optional<Integer> opt) {
        return opt.map(value -> value + 2);
    }
}
```
them in scala
```scala
object MainApp extends App{
  import ConversionsOps.given

  private val opt2:Option[Int] = Some(Integer.valueOf(41))
  private val javaReally: Option[Int] = ReallyJava().addTwo(opt2) // 43
  println(javaReally)
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

