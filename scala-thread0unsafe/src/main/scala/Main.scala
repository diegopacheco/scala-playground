import scala.annotation.threadUnsafe
import scala.collection.parallel.CollectionConverters.*

class Counter:
  @threadUnsafe private var count = 0
  def increment(): Unit = count += 1
  def get: Int = count

@main def main():Unit =
  val counter = new Counter()
  (1 to 1000).par.foreach(_ => counter.increment())
  println(s"Final count: ${counter.get}")
