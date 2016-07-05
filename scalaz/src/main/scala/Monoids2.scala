

object Monoids2 extends App {
    
  trait Monoid[A] {
       def mappend(a1: A, a2: A): A
       def mzero: A
  }
  
  def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  
  object IntMonoid extends Monoid[Int] {
       def mappend(a: Int, b: Int): Int = a + b
       def mzero: Int = 0
  }
  
  implicit val intMonoid = IntMonoid
  val r = sum(List(1, 2, 3, 4))
  println(r)
  
}