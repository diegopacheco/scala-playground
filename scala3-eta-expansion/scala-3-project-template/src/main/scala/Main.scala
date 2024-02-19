object Calculator{
  def sum(va:Int,vb:Int):Int = va + vb
}

@main def hello(): Unit =
   val sumAsFunction = Calculator.sum
   println(sumAsFunction(2,3))

   val sumAsFunction2 = Calculator.sum _
   println(sumAsFunction2(2,3))