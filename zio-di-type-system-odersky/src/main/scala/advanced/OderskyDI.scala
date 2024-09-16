package advanced

import advanced.Providers.{Provider, provide, provided}

object TLP {
  import compiletime.ops.int.S

  trait Select[T <: Tuple, A] {
    def apply(t: T): A
  }

  type IndexOf[T <: Tuple, A] <: Int = T match {
    case A *: _ => 0
    case _ *: rt => S[IndexOf[rt, A]]
  }

  given [T <: NonEmptyTuple, A](using index: ValueOf[IndexOf[T, A]]): Select[T, A] with
    def apply(t: T): A = t.apply(index.value).asInstanceOf[A]
}

object Providers {
  import TLP.*
  opaque type Provider[A] = A

  def provide[A](value: A): Provider[A] = value
  def provided[A](using p: Provider[A]): A = p

  given [T <: Tuple, A](using p: Provider[T], select: Select[T, A]): Provider[A] = select.apply(p)
  given [A, T <: Tuple](using p: Provider[A], pt: Provider[T]): Provider[A *: T] = p *: pt
  given Provider[EmptyTuple] = EmptyTuple
}

import advanced.Providers.*

case class User(name: String, email: String)

class UserSubscriptionService(using Provider[(EmailService, UserDAO)]) {
  def subscribe(user: User): Unit = {
    provided[EmailService].email(user)
    provided[UserDAO].persist(user)
  }
}

class EmailService {
  def email(user: User): Unit = {
    println(s"Email sent to ${user.email}")
  }
}

class UserDAO {
  def persist(user: User): Unit = {
    println(s"User ${user.name} persisted")
  }
}

object OderskyDI extends App {

  given Provider[EmailService] = provide(EmailService())
  given Provider[UserDAO] = provide(UserDAO())
  given Provider[UserSubscriptionService] = provide(UserSubscriptionService())

  val service = provided[UserSubscriptionService]
  service.subscribe(User("John", "jd@jd.com"))
  service.subscribe(User("Marry", "mr@jd.com"))
}