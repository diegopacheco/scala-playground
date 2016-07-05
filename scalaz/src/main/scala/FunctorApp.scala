
object FunctorApp extends App {
    
    import scalaz._
    import Scalaz._
    
    println( List(1, 2, 3) map {_ + 1} )
    
    println( (1, 2, 3) map {_ + 1} )
  
    println( ((x: Int) => x + 1) map {_ * 7} apply 2 )
    
    println( (((_: Int) * 3) map {_ + 100}) (1)  )    
    
    println(  List(1, 2, 3) map {3*} )
    
    val f = Functor[List].lift {(_: Int) * 2}
    println(f(List(3)))
    
    println( List(1, 2, 3) >| "x" )
    
    println( List(1, 2, 3) as "x" )
    
    println( List(1, 2, 3).fpair )
    
    println( List(1, 2, 3).strengthL("x") )
    
    println( List(1, 2, 3).strengthR("x") )
    
    println( List(1, 2, 3).void )
    
    
    
}