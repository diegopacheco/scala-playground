package com.github.diegopacheco.scala.ids.global

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class EmailServiceTest extends AnyFunSuite {

  val emailService = new EmailService(AccountsService)

  test("Get email by valid ID should return correct email") {
    assert(emailService.getEmailByID(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")) == "bananas@gmail.com")
    assert(emailService.getEmailByID(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")) == "apples@gmail.com")
    assert(emailService.getEmailByID(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481")) == "oranges@gmai.com")
  }

  test("Get email by invalid ID should throw RuntimeException") {
    val invalidAccountID = UUID.randomUUID()
    assertThrows[RuntimeException] {
      emailService.getEmailByID(invalidAccountID)
    }
  }
}