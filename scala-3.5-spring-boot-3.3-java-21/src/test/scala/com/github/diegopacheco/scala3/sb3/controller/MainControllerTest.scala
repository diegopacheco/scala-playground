package com.github.diegopacheco.scala3.sb3.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest()
class MainControllerTest {

  @Autowired
  val controller:MainController = null

  @Test
  def testContextLoads(): Unit = {
    assertThat(controller).isNotNull
  }

}
