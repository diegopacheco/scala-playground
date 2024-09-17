import zio._
import zio.stream._

object ZioStreamApp {

  val stream = ZStream.fromIterable(1 to 1000)
  val sink = ZSink.sum[Int]
  val sum = stream.run(sink)

  def main(args: Array[String]): Unit = {
    val runtime = Runtime.default
    Unsafe.unsafe { implicit u =>
      val result = runtime.unsafe.run(sum).getOrThrowFiberFailure()
      println(result)
    }
  }

}
