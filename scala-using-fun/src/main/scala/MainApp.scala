import java.io.{BufferedReader, FileReader}

object MainApp extends App {

  import java.io.PrintWriter
  new PrintWriter("/tmp/test") { write("file contents"); close }
  println("write to file done.")

  import scala.util.Using
  var buf:StringBuffer = new StringBuffer()
  Using(new BufferedReader(new FileReader("/tmp/test"))) { reader =>
    buf.append(reader.readLine())
  }
  println("read done. content:[" + buf.toString + "]")

}
