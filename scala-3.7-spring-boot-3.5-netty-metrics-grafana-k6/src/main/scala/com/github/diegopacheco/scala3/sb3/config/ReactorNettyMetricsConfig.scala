package com.github.diegopacheco.scala3.sb3.config

import org.springframework.boot.web.embedded.netty.NettyServerCustomizer
import org.springframework.stereotype.Component
import reactor.netty.http.server.HttpServer
import java.util.function.Function

@Component
class ReactorNettyMetricsConfig extends NettyServerCustomizer {

  override def apply(httpServer: HttpServer): HttpServer = {
    httpServer.metrics(true, Function.identity[String]())
  }
}
