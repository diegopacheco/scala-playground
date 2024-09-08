import cats.effect.{IO, IOApp}
import fs2.{Stream, text}
import fs2.io.file.{Files, Path}
import cats.effect.unsafe.implicits.global

object Converter extends IOApp.Simple {
  val converter: Stream[IO, Unit] = {
    def fahrenheitToCelsius(f: Double): Double =
      (f - 32.0) * (5.0/9.0)

    Files[IO].readUtf8Lines(Path("testdata/fahrenheit.txt"))
      .filter(s => s.trim.nonEmpty && !s.startsWith("//"))
      .map(line => fahrenheitToCelsius(line.toDouble).toString)
      .intersperse("\n")
      .through(text.utf8.encode)
      .through(Files[IO].writeAll(Path("testdata/celsius.txt")))
  }
  def run: IO[Unit] =
    converter.compile.drain
}

object App extends App{
  Converter.run.unsafeRunSync()
}
