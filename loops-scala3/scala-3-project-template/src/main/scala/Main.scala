@main def hello(): Unit =
  guards()
  println(forExpressions())

def guards():Unit = 
  for
    i <- List(1,2,3,4)
    if i > 2
  do
    println(i)

def forExpressions():IndexedSeq[Int] =
  for i <- (1 to 10) yield i * 2

