import cats.data.EitherT
import cats.implicits.*
import scala.concurrent.{Future, Await}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

type Result[A] = EitherT[Future, String, A]

def validateUserId(id: Int): Result[Int] =
  EitherT(Future {
    if (id > 0) Right(id)
    else Left("Invalid user ID")
  })

def fetchUser(id: Int): Result[String] =
  EitherT(Future {
    if (id == 1) Right("Alice")
    else if (id == 2) Right("Bob")
    else Left("User not found")
  })

def processUser(name: String): Result[String] =
  EitherT(Future {
    if (name.nonEmpty) Right(s"Processed user: $name")
    else Left("Empty user name")
  })

def pipeline(id: Int): Result[String] = for {
  validId <- validateUserId(id)
  userName <- fetchUser(validId)
  result <- processUser(userName)
} yield result

@main def main(): Unit =
  val result1 = Await.result(pipeline(1).value, 5.seconds)
  println(result1)

  val result2 = Await.result(pipeline(0).value, 5.seconds)
  println(result2)

  val result3 = Await.result(pipeline(99).value, 5.seconds)
  println(result3)
