package com.github.diegopacheco.scala.ids.data

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class EmailServiceTest extends AnyFunSuite {

  val accountService: AccountsService.type = AccountsService
  val emailService = new EmailService(accountService)

  test("getEmailByID should return the correct email for a valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val email = emailService.getEmailByID(accountID)
    assert(email == "bananas@gmail.com")
  }

  test("getEmailByID should throw an exception for an invalid account ID") {
    val invalidAccountID = UUID.randomUUID()
    assertThrows[RuntimeException] {
      emailService.getEmailByID(invalidAccountID)
    }
  }

  test("getEmailByID should return the correct email for another valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    val email = emailService.getEmailByID(accountID)
    assert(email == "apples@gmail.com")
  }
}