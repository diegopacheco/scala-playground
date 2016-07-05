
object ApplicativeApp extends App {
    
    import scalaz._
    import Scalaz._
    
    println( List(1, 2, 3, 4) map {(_: Int) * (_:Int)}.curried  map {_(9)}  )
    
    println( 1.point[Option] map {_ + 2} )
    
    println( 9.some <*> {(_: Int) + 3}.some )
    
    println( 1.some *> 2.some )
    
    println( none *> 2.some )
    
    println( 9.some <*> {(_: Int) + 3}.some )
    
    println( 3.some <*> { 9.some <*> {(_: Int) + (_: Int)}.curried.some } )
    
    println(  ^(3.some, 5.some) {_ + _} )
    
    println( List(1, 2, 3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x) )
    
    println( (List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _} )
    
    def sequenceA[F[_]: Applicative, A](list: List[F[A]]): F[List[A]] = list match {
         case Nil     => (Nil: List[A]).point[F]
         case x :: xs => (x |@| sequenceA(xs)) {_ :: _} 
    }
    
    type Function1Int[A] = ({type l[A]=Function1[Int, A]})#l[A]
    
    println( sequenceA(List((_: Int) + 3, (_: Int) + 2, (_: Int) + 1): List[Function1Int[Int]]).apply(3) )
    
}