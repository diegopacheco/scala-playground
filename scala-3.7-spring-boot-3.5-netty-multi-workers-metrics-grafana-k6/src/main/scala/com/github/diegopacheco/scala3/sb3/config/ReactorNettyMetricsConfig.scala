package com.github.diegopacheco.scala3.sb3.config

import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer
import org.springframework.stereotype.Component
import reactor.netty.http.server.HttpServer
import reactor.netty.channel.MicrometerChannelMetricsRecorder
import java.util.function.Supplier

@Component
class ReactorNettyMetricsConfig extends NettyServerCustomizer {

  override def apply(httpServer: HttpServer): HttpServer = {
    val loopResources = new NioEventLoopGroup(48)
    val metricsRecorder: Supplier[MicrometerChannelMetricsRecorder] =
      () => new MicrometerChannelMetricsRecorder("scala-app", "scala-app")

    httpServer
      .runOn(loopResources)
      .metrics(true, metricsRecorder)
      .option(ChannelOption.SO_BACKLOG, Integer.valueOf(4096))
      .option(ChannelOption.TCP_FASTOPEN, Integer.valueOf(1024))
      .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
      .childOption(ChannelOption.SO_KEEPALIVE, java.lang.Boolean.valueOf(true))
      .childOption(ChannelOption.TCP_NODELAY, java.lang.Boolean.valueOf(true))
      .childOption(ChannelOption.SO_RCVBUF, Integer.valueOf(1 * 1024 * 1024))
      .childOption(ChannelOption.SO_SNDBUF, Integer.valueOf(1 * 1024 * 1024))
  }
}
