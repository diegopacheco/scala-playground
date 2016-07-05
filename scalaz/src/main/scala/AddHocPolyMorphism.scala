
object AddHocPolyMorphism extends App {
  
  trait Plus[A] {
       def plus(a1: A, a2: A): A
  }
  
  def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
  
}