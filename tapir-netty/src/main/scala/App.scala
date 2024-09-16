//> using dep com.softwaremill.sttp.tapir::tapir-core:1.11.3
//> using dep com.softwaremill.sttp.tapir::tapir-netty-server-sync:1.11.3

import sttp.tapir.*
import sttp.tapir.server.netty.sync.NettySyncServer

//
// curl -i http://localhost:8080/hello?name=diego
//
@main def nettySyncServer(): Unit =
  val helloWorld = endpoint.get
    .in("hello")
    .in(query[String]("name"))
    .out(stringBody)
    .handleSuccess(name => s"Hello, $name!")

  NettySyncServer().addEndpoint(helloWorld).startAndWait()
