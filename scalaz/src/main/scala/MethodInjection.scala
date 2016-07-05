
object MethodInjection extends App {
  
  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }
  
  object Monoid {
    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }
    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = ""
    }
  }
  
  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    def |+|(a2: A) = F.mappend(value, a2)
  }
  
  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
         val F = implicitly[Monoid[A]]
         val value = a
  }
  
  println(  3 |+| 4 )
  println(  "a" |+| "b" )  

}