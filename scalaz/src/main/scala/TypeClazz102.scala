
object TypeClazz102 extends App {
    
        
    import scalaz._
    import Scalaz._
    
    case class TrafficLight(name: String)
    
    val red = TrafficLight("red")
    val yellow = TrafficLight("yellow")
    val green = TrafficLight("green")
    
    implicit val TrafficLightEqual:Equal[TrafficLight] = Equal.equal(_ == _)
    println( red === yellow )
    
    
}