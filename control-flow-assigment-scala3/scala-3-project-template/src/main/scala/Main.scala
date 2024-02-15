@main def hello(): Unit =
   var num:Int = 1
   val even = if num%2==0 then true else false
   val odd = 
    if num%2!=0 then
      true
    else
      false
   println(even)
   println(odd)
   println(isEven(2))
   print5()

def isEven(num: Int) = if num%2==0 then true else false

def print5(): Unit = for i <- List(1, 2, 3, 4, 5) do println(i)