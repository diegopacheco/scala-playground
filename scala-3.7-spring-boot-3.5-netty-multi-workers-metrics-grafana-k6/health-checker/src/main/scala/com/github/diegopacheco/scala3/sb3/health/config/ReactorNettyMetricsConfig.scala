package com.github.diegopacheco.scala3.sb3.health.config

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
    val loopResources = new NioEventLoopGroup(2)
    val metricsRecorder: Supplier[MicrometerChannelMetricsRecorder] =
      () => new MicrometerChannelMetricsRecorder("health-checker", "health-checker")

    httpServer
      .runOn(loopResources)
      .metrics(true, metricsRecorder)
      .option(ChannelOption.SO_BACKLOG, Integer.valueOf(2048))
      .option(ChannelOption.TCP_FASTOPEN, Integer.valueOf(512))
      .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
      .childOption(ChannelOption.SO_KEEPALIVE, java.lang.Boolean.valueOf(true))
      .childOption(ChannelOption.TCP_NODELAY, java.lang.Boolean.valueOf(true))
      .childOption(ChannelOption.SO_RCVBUF, Integer.valueOf(512 * 1024))
      .childOption(ChannelOption.SO_SNDBUF, Integer.valueOf(512 * 1024))
  }
}
