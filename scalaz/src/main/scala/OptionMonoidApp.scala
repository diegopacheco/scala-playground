
object OptionMonoidApp extends App {
    
    import scalaz._
    import Scalaz._
  
    println( (none: Option[String]) |+| "andy".some )
    
    println( Tags.First('a'.some) |+| Tags.First('b'.some) )
    
    println( Tags.First(none: Option[Char]) |+| Tags.First('b'.some) )
    
    println( Tags.First('a'.some) |+| Tags.First(none: Option[Char]) )
}