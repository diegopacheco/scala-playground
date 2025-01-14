package com.github.diegopacheco.scala.ids.services

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class AccountsTest extends AnyFunSuite {

  test("Create account should return a valid UUID") {
    val accountID = AccountsService.createAccount()
    assert(accountID.isInstanceOf[UUID])
  }

  test("Link service to a valid account should return true") {
    val accountID = AccountsService.createAccount()
    assert(AccountsService.link(accountID, "EmailService", 20_001))
  }

  test("Link service to an invalid account should return false") {
    val invalidAccountID = UUID.randomUUID()
    assert(!AccountsService.link(invalidAccountID, "EmailService", 20_001))
  }

  test("get accounts linked by id") {
    val accountID = AccountsService.createAccount()
    AccountsService.link(accountID, "EmailService", 20_001)
    val accounts = AccountsService.getLinksByAccount(accountID)
    assert(accounts.size == 1)
    assert(accounts.head._1 == "EmailService")
    assert(accounts.head._2 == 20_001)

    AccountsService.link(accountID, "LoginService", 10_001)
    val accounts2 = AccountsService.getLinksByAccount(accountID)
    assert(accounts2.size == 2)
    assert(accounts2("LoginService") == 10_001)
  }
}