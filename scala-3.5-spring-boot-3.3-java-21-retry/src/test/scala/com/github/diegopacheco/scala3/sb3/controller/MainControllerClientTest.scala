package com.github.diegopacheco.scala3.sb3.controller

import com.github.diegopacheco.scala3.sb3.config.HttpClientDriver
import com.github.diegopacheco.scala3.sb3.controller.config.ClientConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(Array(classOf[ClientConfig]))
class MainControllerClientTest {

  @LocalServerPort
  private val port = 0

  @Autowired
  val restTemplate: RestTemplate = null

  @Test
  def testIndex(): Unit = {
    val result = this.restTemplate.getForObject("http://localhost:" + port + "/",
      classOf[String])
    assertThat(result).contains("name")
    assertThat(result).contains("message")
  }

}

