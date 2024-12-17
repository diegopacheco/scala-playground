package com.github.diegopacheco.scalaplayground.pekko.springboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(Array("com.github.diegopacheco.scalaplayground.pekko.springboot.*"))
class SpringScalaApplication

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringScalaApplication], args*)
  }
}