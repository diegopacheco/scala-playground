import zio._

object ZioDiAdvancedApp extends ZIOAppDefault {

  private val ok = ZIO.succeed("ok").debug("ok")
  private val fail = ZIO.fail("Oopsy Daisy").debug("fail")

  private val program = for {
    _ <- ok
    _ <- fail
  } yield ()

  def run: URIO[Any, ExitCode] = program
    .catchAll(err => ZIO.debug(s"Caught error: $err"))
    .exitCode

}