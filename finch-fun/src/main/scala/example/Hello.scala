package example

import io.finch._
import com.twitter.finagle.Http
import com.twitter.util.Await

object Hello extends App {
  val api: Endpoint[String] = get("hello") { Ok("Hello, World!") }
  lazy val server = Http.server.serve(":8080", api.toServiceAs[Text.Plain])
  Await.ready(server)
}
