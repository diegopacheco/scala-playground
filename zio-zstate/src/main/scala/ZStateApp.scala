import zio._
import java.io.IOException

object ZStateApp extends zio.ZIOAppDefault {
  val myApp: ZIO[ZState[Int], IOException, Unit] = for {
    s <- ZIO.service[ZState[Int]]
    _ <- s.update(_ + 1)
    _ <- s.update(_ + 2)
    state <- s.get
    _ <- Console.printLine(s"current state: $state")
  } yield ()

  def run = ZIO.stateful(0)(myApp)
}