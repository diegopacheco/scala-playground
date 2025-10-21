package com.github.diegopacheco.scala3.sb3.slow

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import reactor.core.scheduler.Schedulers

@SpringBootApplication
@ComponentScan(Array("com.github.diegopacheco.scala3.sb3.core", "com.github.diegopacheco.scala3.sb3.slow"))
class SlowEndpointsApplication

object SlowEndpointsApplication {
  def main(args: Array[String]): Unit = {
    Schedulers.enableMetrics()
    SpringApplication.run(classOf[SlowEndpointsApplication])
  }
}
