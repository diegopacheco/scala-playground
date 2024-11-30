package com.github.diegopacheco.scalatraining

import com.github.diegopacheco.scalatraining.E04.Json4sSupport
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E05Tests extends AnyFlatSpec with should.Matchers with Json4sSupport:

  "E05 /tolls" should "have tolls and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E05Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val resultJson = scala.io.Source.fromURL("http://localhost:8080/tolls").mkString
    val result = org.json4s.native.Serialization.read[List[E05.TollModel]](resultJson)
    result.size should be > 0
    thread.interrupt()
  }

  "E05 /pass/car/CAZ123" should "pass the toll and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E05Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val plate = "CAZ123"
    val url = s"http://localhost:8080/pass/car/$plate"
    val connection = new java.net.URL(url).openConnection().asInstanceOf[java.net.HttpURLConnection]
    connection.setRequestMethod("POST")
    connection.setDoOutput(true)
    val responseCode = connection.getResponseCode
    responseCode should be(200)

    val buyResponse = scala.io.Source.fromInputStream(connection.getInputStream).mkString
    buyResponse should include(s"Vehicle car with license plate ${plate} pass the toll successfully")

    val resultJson = scala.io.Source.fromURL("http://localhost:8080/tolls").mkString
    val result = org.json4s.native.Serialization.read[List[E05.TollModel]](resultJson)
    result.size should be > 0

    thread.interrupt()
  }

  "E05 /sales" should "return sales for today and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E05Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val currentDate = java.time.LocalDate.now().toString
    val resultJson = scala.io.Source.fromURL(s"http://localhost:8080/sales/$currentDate").mkString
    val result = org.json4s.native.Serialization.read[List[E05.TollModel]](resultJson)
    result.size should be >= 0
    thread.interrupt()
  }