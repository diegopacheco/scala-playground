error id: file://<WORKSPACE>/src/main/scala/Main.scala:scala/Predef.String#
file://<WORKSPACE>/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: scala/Predef.String#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -String#
	 -scala/Predef.String#
offset: 606
uri: file://<WORKSPACE>/src/main/scala/Main.scala
text:
```scala
import scala.compiletime.{constValue, error, erasedValue}

opaque type Email <: String = String

object Email:
  // Compile-time validation for specific known email patterns
  transparent inline def apply[S <: String & Singleton](inline value: S): Email = 
    inline erasedValue[S] match
      case _: "user@domain.com" => value
      case _: "test@example.org" => value
      case _: "admin@company.net" => value
      case _: "user@sdomain.com" => value  // Adding the new email to valid list
      case _ => error("Email not in the predefined valid list at compile time")
  
  def fromString(value: Str@@ing): Email = 
    if (value.contains("@")) value
    else throw new IllegalArgumentException("Invalid email format")
  
  extension (email: Email) def domain: String = 
    email.split("@").last

@main def main():Unit =
  val validEmail = Email("user@sdomain.com")
  println(s"Valid email: $validEmail")
  println(s"Domain: ${validEmail}")
  
  val runtimeEmail = Email.fromString("another@example.com")
  println(s"Runtime validated email: $runtimeEmail")
```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Predef.String#