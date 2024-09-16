import zio._

trait Terminal {
  def print(line: Any): Task[Unit]
  def printLine(line: Any): Task[Unit]
  def readLine: Task[String]
}

object TerminalLive extends Terminal {
  override def print(line: Any): Task[Unit] =
    ZIO.attemptBlocking(scala.Predef.print(line))

  override def printLine(line: Any): Task[Unit] =
    ZIO.attemptBlocking(scala.Predef.println(line))

  override def readLine: Task[String] =
    ZIO.attemptBlocking(scala.io.StdIn.readLine())
}

object MainApp extends ZIOAppDefault {
  def myApp(c: Terminal): Task[Unit] =
    for {
      _    <- c.print("Please enter your name: ")
      name <- c.readLine
      _    <- c.printLine(s"Hello, $name!")
    } yield ()

  def run = myApp(TerminalLive)
}