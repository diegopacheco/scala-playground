trait Key[T]:
  type Value = T

case class TypedKey[T](name: String) extends Key[T]

class TypedMap private (private val data: Map[String, Any]):
  def put[T](key: TypedKey[T], value: T): TypedMap = 
    TypedMap(data + (key.name -> value))
    
  def get[T](key: TypedKey[T]): Option[T] = 
    data.get(key.name).asInstanceOf[Option[T]]

object TypedMap:
  def empty: TypedMap = TypedMap(Map.empty)

@main def main():Unit =
  val nameKey = TypedKey[String]("name")
  val ageKey = TypedKey[Int]("age")
  
  val map = TypedMap.empty
    .put(nameKey, "Alice")
    .put(ageKey, 30)
  
  println(map.get(nameKey))
