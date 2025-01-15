package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class LoginTest extends AnyFunSuite {
  val accountService: AccountsService.type = AccountsService
  val loginService = new LoginService(accountService)

  test("login should return true for a valid account ID and password") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    assert(loginService.login(Account.fromUUID(accountID), "bananas"))
  }

  test("login should return false for an invalid account ID") {
    val accountID = UUID.randomUUID()
    assert(!loginService.login(Account.fromUUID(accountID), "bananas"))
  }

  test("login should return false for a valid account ID and incorrect password") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    assert(!loginService.login(Account.fromUUID(accountID), "wrongpassword"))
  }

  test("login should return true for another valid account ID and password") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    assert(loginService.login(Account.fromUUID(accountID), "apples"))
  }

  test("login should return true for a third valid account ID and password") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481")
    assert(loginService.login(Account.fromUUID(accountID), "oranges"))
  }
}
