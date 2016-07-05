
object BasicScalaZ extends App {
  
    import scalaz._
    import Scalaz._
  
    println( 1 === 1)
    
    println( "x" === "x")
    println( "x" === "y")
    
    println( 1.0 ?|? 2.0 ) 
    
    println( 1.0 max 2.0 )
    
    "hello".println

}