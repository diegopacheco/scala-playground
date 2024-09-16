import zio.*

case class User(name:String,email:String)

class UserSubscriptionService(emailService: EmailService, userDAO: UserDAO) {
  def subscribe(user: User): ZIO[Any, Nothing, Unit] = {
    for {
      _ <- ZIO.succeed(emailService.email(user))
      _ <- ZIO.succeed(userDAO.persist(user))
    } yield ()
  }
}

class EmailService{
  def email(user:User): Unit = {
    println(s"Email sent to ${user.email}")
  }
}

class UserDAO{
  def persist(user:User): Unit = {
    println(s"User ${user.name} persisted")
  }
}

object ZioDIApp extends ZIOAppDefault{

  val program = for {
      service <- ZIO.service[UserSubscriptionService]
      _ <- service.subscribe(User("John","jd@jd.com"))
      _ <- service.subscribe(User("Marry","mr@jd.com"))
  } yield ()

  def run = program.provide(
    ZLayer.succeed(new EmailService),
    ZLayer.succeed(new UserDAO),
    ZLayer.fromFunction(new UserSubscriptionService(_, _))
  )

}