@main def main():Unit =
  
  import scala.util.boundary, boundary.break

  def findIndex[T](list: List[T], target: T): Int =
    boundary:
      for (item, idx) <- list.zipWithIndex do
        if item == target then break(idx)
      -1

  val numbers = List(10, 20, 30, 40)
  println(findIndex(numbers, 30))
