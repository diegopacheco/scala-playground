import scala.util.NotGiven
def printLists[A, B](la:List[A],lb:List[B]): Unit =
  la.foreach(println)
  lb.foreach(println)

def printDifferentLists[A, B](la:List[A],lb:List[B])(using NotGiven[A =:= B]): Unit =
  la.foreach(println)
  lb.foreach(println)

@main def hello(): Unit =
  printLists(List(1,2,3),List(4,5,6))
  printDifferentLists(List(10,20,30),List("a","b","c"))
  
//  E172 - Compilation Error
//  printDifferentLists(List(1,2,3),List(4,5,6))
//  [error]    |                                ^
//  [error]    |No given instance of type util.NotGiven[Int =:= Int] was found for parameter x$3 of method printDifferentLists
