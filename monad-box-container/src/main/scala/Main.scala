case class Box[+T](value: T):
    def map[U](f: T => U): Box[U] = Box(f(value))
    def flatMap[U](f: T => Box[U]): Box[U] = f(value)
    def filter(predicate: T => Boolean): Option[Box[T]] = 
      if predicate(value) then Some(this) else None
    def withFilter(predicate: T => Boolean): Box[T] = 
      if predicate(value) then this else throw new NoSuchElementException("Predicate does not hold")

@main def main():Unit =
  val result = for
    x <- Box(5)
    y <- Box(10)
    z = x + y
    if z > 10
  yield z

  println(result)
