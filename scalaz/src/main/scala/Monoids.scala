

object Monoids extends App {
    
    object IntMonoid {
        def mappend(a: Int, b: Int): Int = a + b
        def mzero: Int = 0
    }
    
    def sum(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)
    
    val r = sum(List(1, 2, 3, 4))
    println(r)
    
}