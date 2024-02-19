/**
   * 0. Kind                 - Type of Types
   * 1. Value Types          - String, Int value is attached to type
   * 2. Generics             - List, Option 
   * 3. Generics of Generics - Functor, Monads
   */
object AdvancedTypeSystemTypeLambdas{
  val number:Int = 42                          // type 0
  val fortyTwo:42 = 42                         // type 1 
  val lista:List[Int] = List(1,2,3)            // type 2 
  
  class Functor[F[_]]                          // type 3
  val functorOption = new Functor[Option]      // type 3  

  type MyList = [T] =>> List[T]
  val langs:MyList[String] = List("Scala 3","Zig","Rust","Kotlin")  

  type MapWithStringKeyT = [T] =>> Map[String, T]
  val citiesAge:MapWithStringKeyT[Int] = Map( "Porto Alegre" -> 251,
                                              "Sao Paulo" -> 470,
                                              "Florianopolis" -> 470,
                                              "Salvador " -> 475,
                                              "Rio de Janeiro" -> 458)
  
  type MyEither = [T,E] =>> Either[E, Option[T]]
  val me:MyEither[Int,String] = Right(Some(2))
}

@main def hello(): Unit = 
  println("Type Systems Rocks")
  
  import AdvancedTypeSystemTypeLambdas._
  println(langs)
  println(citiesAge)
  println(me)