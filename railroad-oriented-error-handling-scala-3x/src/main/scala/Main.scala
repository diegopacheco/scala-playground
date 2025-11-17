//
// ROP: Railway Oriented Programming
// https://fsharpforfunandprofit.com/rop/
//
// EitherT (Good read on Monad Transformers for Cats)
// https://benmosheron.gitlab.io/blog/2018/11/20/eithert.html
//
//
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

enum ValidationError:
  case EmptyName
  case InvalidAge(age: Int)
  case InvalidEmail(email: String)

case class Person(name: String, age: Int, email: String)

def validateName(name: String): Future[Either[ValidationError, String]] =
  Future {
    if name.trim.isEmpty then Left(ValidationError.EmptyName)
    else Right(name.trim)
  }

def validateAge(age: Int): Future[Either[ValidationError, Int]] =
  Future {
    if age < 0 || age > 120 then Left(ValidationError.InvalidAge(age))
    else Right(age)
  }

def validateEmail(email: String): Future[Either[ValidationError, String]] =
  Future {
    if email.contains("@") then Right(email)
    else Left(ValidationError.InvalidEmail(email))
  }

def createPerson(name: String, age: Int, email: String): Future[Either[ValidationError, Person]] =
  for
    nameResult  <- validateName(name)
    ageResult   <- validateAge(age)
    emailResult <- validateEmail(email)
  yield
    for
      validName  <- nameResult
      validAge   <- ageResult
      validEmail <- emailResult
    yield Person(validName, validAge, validEmail)

def processRequest(person: Person): Future[Either[ValidationError, String]] =
  Future(Right(s"Processed: ${person.name}, Age: ${person.age}, Email: ${person.email}"))

def fullPipeline(name: String, age: Int, email: String): Future[Either[ValidationError, String]] =
  for
    personResult <- createPerson(name, age, email)
    result <- personResult match
      case Right(person) => processRequest(person)
      case Left(error)   => Future.successful(Left(error))
  yield result

@main def main(): Unit =
  val pipeline = for
    result1 <- fullPipeline("Diego", 30, "diego@email.com")
    result2 <- fullPipeline("", 30, "diego@email.com")
    result3 <- fullPipeline("Diego", -5, "diego@email.com")
    result4 <- fullPipeline("Diego", 30, "invalid")
  yield
    println(s"Valid case: $result1")
    println(s"Empty name: $result2")
    println(s"Invalid age: $result3")
    println(s"Invalid email: $result4")

  Await.result(pipeline, 5.seconds)
