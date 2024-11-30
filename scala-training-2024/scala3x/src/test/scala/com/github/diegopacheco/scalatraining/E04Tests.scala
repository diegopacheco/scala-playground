package com.github.diegopacheco.scalatraining

import com.github.diegopacheco.scalatraining.E04.Json4sSupport
import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class E04Tests extends AnyFlatSpec with should.Matchers with Json4sSupport:

  "E04 /products" should "have products and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E04Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val resultJson = scala.io.Source.fromURL("http://localhost:8080/products").mkString
    val result = org.json4s.native.Serialization.read[List[E04.ProductModel]](resultJson)
    result.size should be > 0
    thread.interrupt()
  }

  "E04 /buy" should "buy one Coke and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E04Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val productId = 1
    val url = s"http://localhost:8080/buy/$productId"
    val connection = new java.net.URL(url).openConnection().asInstanceOf[java.net.HttpURLConnection]
    connection.setRequestMethod("POST")
    connection.setDoOutput(true)
    val responseCode = connection.getResponseCode
    responseCode should be(200)

    val buyResponse = scala.io.Source.fromInputStream(connection.getInputStream).mkString
    buyResponse should include("Product 1 bought successfully.")

    val resultJson = scala.io.Source.fromURL("http://localhost:8080/products").mkString
    val result = org.json4s.native.Serialization.read[List[E04.ProductModel]](resultJson)
    val coke = result.find(_.id == productId).get
    coke.stock should be > 0

    thread.interrupt()
  }

  "E04 /sales" should "return sales for today and work" in {
    val thread = new Thread(new Runnable {
      override def run(): Unit = {
        E04Main.main(Array())
      }
    })
    thread.start()
    Thread.sleep(2000)

    val currentDate = java.time.LocalDate.now().toString
    val resultJson = scala.io.Source.fromURL(s"http://localhost:8080/sales/$currentDate").mkString
    val result = org.json4s.native.Serialization.read[List[E04.SalesProductModel]](resultJson)
    result.size should be >= 0
    thread.interrupt()
  }