@main def main():Unit =
  enum Json:
    case Null
    case Bool(value: Boolean)
    case Num(value: Double)
    case Str(value: String)
    case Arr(values: List[Json])
    case Obj(fields: Map[String, Json])
    
    def \(key: String): Option[Json] = this match
      case Obj(fields) => fields.get(key)
      case _ => None

  val json = Json.Obj(Map(
    "name" -> Json.Str("Alice"),
    "age" -> Json.Num(30),
    "active" -> Json.Bool(true)
  ))

  println(json \ "name") 
