import scala.compiletime.{constValue, error, erasedValue}

opaque type Email <: String = String

object Email:
  transparent inline def apply[S <: String & Singleton](inline value: S): Email = 
    inline erasedValue[S] match
      case _: "user@domain.com" => value
      case _: "test@example.org" => value
      case _: "admin@company.net" => value
      case _: "user@sdomain.com" => value
      case _ => error("Email not in the predefined valid list at compile time")
  
  def fromString(value: String): Email = 
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