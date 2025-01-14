package com.github.diegopacheco.scala.ids.services
import org.scalatest.funsuite.AnyFunSuite

class EmailTest extends AnyFunSuite {

  test("Get email by valid ID should return correct email") {
    assert(EmailService.getEmailByID(20_001) == "bananas@gmail.com")
    assert(EmailService.getEmailByID(20_002) == "apples@gmail.com")
    assert(EmailService.getEmailByID(20_003) == "oranges@gmai.com")
  }

  test("Get email by invalid ID should return empty string") {
    assert(EmailService.getEmailByID(20_004) == "")
  }
}