package com.github.diegopacheco.scala3.sb3.config

import org.springframework.stereotype.Component

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}

@Component
class HttpClientDriver(
  val client: HttpClient = HttpClient.newHttpClient()
) {

  val apiUrl = "https://catfact.ninja/fact"
  var attempts = 0
  val maxAttempts = 3

  def get(): String = {
    val request = HttpRequest.newBuilder()
      .uri(URI.create(apiUrl))
      .build()

    while (attempts < maxAttempts) {
      try {
        println(s"Attempt: $attempts - Request: $request ...")
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() == 200) {
          val responseBody = response.body()
          val factStart = responseBody.indexOf("\"fact\":\"") + 8
          val factEnd = responseBody.indexOf("\"", factStart)
          return responseBody.substring(factStart, factEnd)
        } else {
          attempts += 1
          if (attempts >= maxAttempts) {
            throw new RuntimeException(s"Error getting fact after 3 attempts, status code: ${response.statusCode()}")
          }
        }
      } catch {
        case e: Exception =>
          attempts += 1
          if (attempts >= maxAttempts) {
            e.printStackTrace()
            throw new RuntimeException("Error getting fact after 3 attempts", e)
          }
      }
    }
    throw new RuntimeException("Unexpected error")
  }

}
