package com.github.diegopacheco.scala3.sb3.fast

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import reactor.core.scheduler.Schedulers

@SpringBootApplication
@ComponentScan(Array("com.github.diegopacheco.scala3.sb3.core", "com.github.diegopacheco.scala3.sb3.fast"))
@EnableJdbcRepositories(basePackages = Array("com.github.diegopacheco.scala3.sb3.core.repository"))
class FastEndpointsApplication

object FastEndpointsApplication {
  def main(args: Array[String]): Unit = {
    Schedulers.enableMetrics()
    SpringApplication.run(classOf[FastEndpointsApplication])
  }
}
