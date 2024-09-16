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

object UserSubscriptionService {
  val layer: ZLayer[EmailService & UserDAO, Nothing, UserSubscriptionService] =
    ZLayer.fromFunction(new UserSubscriptionService(_, _))
}

class EmailService{
  def email(user:User): ZIO[Any, Nothing, Unit] = {
    ZIO.succeed(println(s"Email sent to ${user.email}"))
  }
}
object EmailService{
  val layer = ZLayer.succeed(new EmailService)
}

class UserDAO{
  def persist(user: User): ZIO[Any, Nothing, Unit] = {
    ZIO.succeed(println(s"User ${user.name} persisted"))
  }
}
object UserDAO{
  val layer = ZLayer.succeed(new UserDAO)
}

object ZioDIApp extends ZIOAppDefault{

  val program = for {
      service <- ZIO.service[UserSubscriptionService]
      _ <- service.subscribe(User("John","jd@jd.com"))
      _ <- service.subscribe(User("Marry","mr@jd.com"))
  } yield ()

  def run = program.provide(
    EmailService.layer,
    UserDAO.layer,
    UserSubscriptionService.layer
  )

}