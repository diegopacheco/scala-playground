def add(x: Int, y: Int): Int = x + y

@main def hello() =
  val a = List(1,2,3,4)
  println(a.reduce( (a,b) => a + b ))
  println(a.reduce(add))
  println(a.reduce(_+_))
  
  println(a.reduce(_ * _))
  println(a.product)
