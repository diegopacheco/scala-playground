package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class EmailTest extends AnyFunSuite {
  val accountService: AccountsService.type = AccountsService
  val emailService = new EmailService(accountService)

  test("getEmailByID should return the correct email for an existing account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val email = emailService.getEmailByID(Account.fromUUID(accountID))
    assert(email == "bananas@gmail.com")
  }

  test("getEmailByID should throw an exception for a non-existent account ID") {
    val accountID = UUID.randomUUID()
    assertThrows[RuntimeException] {
      emailService.getEmailByID(Account.fromUUID(accountID))
    }
  }

  test("getEmailByID should return the correct email for another existing account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    val email = emailService.getEmailByID(Account.fromUUID(accountID))
    assert(email == "apples@gmail.com")
  }

  test("getEmailByID should return the correct email for a third existing account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481")
    val email = emailService.getEmailByID(Account.fromUUID(accountID))
    assert(email == "oranges@gmai.com")
  }
}
