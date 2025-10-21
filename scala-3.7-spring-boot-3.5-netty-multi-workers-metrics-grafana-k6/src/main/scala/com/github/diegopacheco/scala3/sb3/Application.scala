package com.github.diegopacheco.scala3.sb3

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import reactor.core.scheduler.Schedulers

@SpringBootApplication
class Application

object Application {
  def main(args: Array[String]): Unit = {
    Schedulers.enableMetrics()
    SpringApplication.run(classOf[Application])
  }
}
