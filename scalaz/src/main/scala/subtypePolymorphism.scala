
// Something must be son of Plus
trait Plus[A <: Plus[A]]{
   def plus(a2:A):A
}

class PlusInt(var v:Int) extends Plus[PlusInt] {
   def get():Int = v
   override def plus(a2:PlusInt):PlusInt = {
       val r = a2.get() + v
       this.v = r
       return this
   }
   override def toString():String = "Scala Sub-Type Polymorphism is crazy: " + get()
}

object subtypePolymorphismMain extends App {
        
   def plus[A <: Plus[A]](a1: A, a2: A): A = a1.plus(a2)
   
   val a = new PlusInt(10)
   val b = new PlusInt(6)
   val r = plus(a, b)
   
   println(r)
   
}

