def sayHello(f: () => Unit): Unit = 
  println("Before calling the function as param. ")
  f()
  println("After calling the function")

@main def hello(): Unit =
   val lambdaFunction = () => println("Scala 3 rocks!")
   sayHello(lambdaFunction)

   val lp = lazyPrinter("Lazy --> Scala 3 High order functions")
   lp()

def lazyPrinter(msg: String): ()=> Unit = 
  () => println(msg)