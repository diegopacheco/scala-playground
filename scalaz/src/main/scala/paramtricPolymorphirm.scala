object ParametetricPolymorphismMain extends App {

	case class Car(make: String)

	def head[A](xs: List[A]): A = xs(0)

	println( head(1 :: 2 :: Nil) )
	println( head(Car("Civic") :: Car("CR-V") :: Nil) )

}