package com.github.diegopacheco.scalatraining

import com.github.diegopacheco.scalatraining.E04.Json4sSupport
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E06Tests extends AnyFlatSpec with should.Matchers with Json4sSupport:

  "E05 /sales" should "return sales for the SATE today and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E06Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val currentDate = java.time.LocalDate.now().toString
    val result = scala.io.Source.fromURL(s"http://localhost:8080/sales/$currentDate").mkString
    result.size should be >= 0
    println(result)
    thread.interrupt()
  }