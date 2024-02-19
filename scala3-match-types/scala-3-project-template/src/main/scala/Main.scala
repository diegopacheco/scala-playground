// Match type - allow us to make compound types
// we can create 1 generic function to handle all this type
// ...otherwise 3 functions one for each type
type SupportedTypes[T] = T match
  case Int => Int
  case String => String
  case List[t] => t

// we do pattern match to handle the match type  
def lastValue[T](thing:T): SupportedTypes[T] = thing match
  case i:Int => (i%10).toInt
  case s:String => if s.isEmpty() then "" else s.charAt(s.length()-1)+""
  case l:List[_] => if l.isEmpty then throw new IllegalStateException else l.last

@main def hello(): Unit =
  val result = 42
  println(lastValue(result))       // 2
  println(lastValue("okay?"))      // ?
  println(lastValue(List(1,2,3)))  // 3