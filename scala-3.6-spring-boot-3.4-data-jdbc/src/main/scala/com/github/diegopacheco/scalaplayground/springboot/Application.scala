package com.github.diegopacheco.scalaplayground.springboot

import com.github.diegopacheco.scalaplayground.springboot.dao.DBLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{Bean, ComponentScan}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args*)
  }
}

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(Array("com.github.diegopacheco.scalaplayground.springboot.*"))
class Application(
  @Autowired val loder: DBLoader
) {

  @Bean
  def commandLineRunner(ctx: ApplicationContext): CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = {
      System.out.println("Spring Boot 3.4.0 up and running! ")
      loder.insertData()
      System.out.println("DB Loader done. ")
    }
  }

}