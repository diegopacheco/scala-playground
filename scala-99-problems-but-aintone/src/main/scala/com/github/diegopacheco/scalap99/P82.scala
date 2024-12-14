package com.github.diegopacheco.scalap99

// P82 (*) Cycle from a given node.
//     Write a method named findCycles to find closed paths (cycles) starting at
//     a given node in a graph.  The method should return all cycles.
//
//     scala> Graph.fromString("[b-c, f-c, g-h, d, f-b, k-f, h-g]").findCycles("f")
//     res0: List[List[String]] = List(List(f, c, b, f), List(f, b, c, f))
//
// Wrong - some bug - will fix later.
//
object P82 {

  import P81._

  def findCycles[T](g: Graph[T, Unit], source: T): List[List[T]] = {
    def findCyclesR(visited: List[T], current: T): List[List[T]] = {
      if (visited.contains(current)) {
        if (current == source) List((current :: visited).reverse)
        else Nil
      } else {
        g.edges.flatMap { e =>
          if (e.n1.value == current && !visited.contains(e.n2.value))
            findCyclesR(current :: visited, e.n2.value)
          else if (e.n2.value == current && !visited.contains(e.n1.value))
            findCyclesR(current :: visited, e.n1.value)
          else
            Nil
        }
      }
    }

    findCyclesR(List(), source).filter(_.head == source)
  }
}

object P82Main extends App {
  import P82._
  import P81._
  val g = Graph.fromString("[b-c, f-c, g-h, d, f-b, k-f, h-g]").asInstanceOf[Graph[String, Unit]]
  println(findCycles(g, "f"))
}