package com.github.diegopacheco.scala3.sb3.config

import com.github.diegopacheco.scala3.sb3.controller.config.ClientConfig
import org.assertj.core.api.Assertions.{assertThat, assertThatThrownBy}
import org.junit.jupiter.api.Test
import org.mockito.Mockito.{mock, when}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import

import java.net.URI
import java.net.http.HttpResponse.BodyHandlers
import java.net.http.{HttpClient, HttpRequest, HttpResponse}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(Array(classOf[ClientConfig]))
class HttpClientDriverTest {

  @LocalServerPort
  private val port = 0

  @Autowired
  private val driver: HttpClientDriver = null

  @Test
  def testGetFact(): Unit = {
    val result = driver.get()
    assertThat(result).isNotNull
    println(s"Fact: $result")
  }

  @Test
  def testGetFactWithRetry(): Unit = {
    val mockClient = mock(classOf[HttpClient])
    val mockRequest = HttpRequest.newBuilder().uri(URI.create("https://catfact.ninja/fact")).build()
    val mockResponse500 = mock(classOf[HttpResponse[String]])
    val mockResponse200 = mock(classOf[HttpResponse[String]])

    when(mockResponse500.statusCode()).thenReturn(500)
    when(mockResponse200.statusCode()).thenReturn(200)
    when(mockResponse200.body()).thenReturn("""{"fact":"Cats have five toes on their front paws, but only four toes on their back paws."}""")

    when(mockClient.send(mockRequest, BodyHandlers.ofString()))
      .thenReturn(mockResponse500)
      .thenReturn(mockResponse200)

    val driverWithMock = new HttpClientDriver(mockClient)

    val result = driverWithMock.get()
    assertThat(result).isEqualTo("Cats have five toes on their front paws, but only four toes on their back paws.")
    println(s"Fact: $result")
  }

  @Test
  def testGetFactWithRetryFailure(): Unit = {
    val mockClient = mock(classOf[HttpClient])
    val mockRequest = HttpRequest.newBuilder().uri(URI.create("https://catfact.ninja/fact")).build()
    val mockResponse500 = mock(classOf[HttpResponse[String]])

    when(mockResponse500.statusCode()).thenReturn(500)

    when(mockClient.send(mockRequest, BodyHandlers.ofString()))
      .thenReturn(mockResponse500)
      .thenReturn(mockResponse500)
      .thenReturn(mockResponse500)

    val driverWithMock = new HttpClientDriver(mockClient)
    assertThatThrownBy(() => driverWithMock.get()).isInstanceOf(classOf[RuntimeException])
  }

  @Test
  def testGetFactWithRetryExceptionRecovery(): Unit = {
    val mockClient = mock(classOf[HttpClient])
    val mockRequest = HttpRequest.newBuilder().uri(URI.create("https://catfact.ninja/fact")).build()
    val mockResponse200 = mock(classOf[HttpResponse[String]])

    when(mockResponse200.statusCode()).thenReturn(200)
    when(mockResponse200.body()).thenReturn("""{"fact":"Cats have five toes on their front paws, but only four toes on their back paws."}""")

    when(mockClient.send(mockRequest, BodyHandlers.ofString()))
      .thenThrow(new RuntimeException("Temporary error"))
      .thenThrow(new RuntimeException("Temporary error"))
      .thenReturn(mockResponse200)

    val driverWithMock = new HttpClientDriver(mockClient)

    val result = driverWithMock.get()
    assertThat(result).isEqualTo("Cats have five toes on their front paws, but only four toes on their back paws.")
    println(s"Fact: $result")
  }

}
