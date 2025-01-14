package com.github.diegopacheco.scala.ids.global

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class LoginServiceTest extends AnyFunSuite {

  val loginService = new LoginService(AccountsService)

  test("Login with valid account ID and correct password should return true") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    assert(loginService.login(accountID, "bananas"))
  }

  test("Login with valid account ID and incorrect password should return false") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    assert(!loginService.login(accountID, "wrongpassword"))
  }

  test("Login with invalid account ID should return false") {
    val invalidAccountID = UUID.randomUUID()
    assert(!loginService.login(invalidAccountID, "anyPassword"))
  }
}