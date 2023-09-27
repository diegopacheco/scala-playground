import zio.*
import zio.http.*
import zio.http.model.Method

import java.util.UUID

object UUIDServer extends ZIOAppDefault {

  val app: App[Any] =
    Http.collect[Request] {
      case Method.GET -> root / "u" => Response.text(UUID.randomUUID().toString)
    }

  override val run =
    println("Scala 3.x zio-http running on http://127.0.0.1:8080/u")
    Server.serve(app).provide(Server.default)
}