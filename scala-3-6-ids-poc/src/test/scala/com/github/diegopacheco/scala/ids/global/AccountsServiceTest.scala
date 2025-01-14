package com.github.diegopacheco.scala.ids.global

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class AccountsServiceTest extends AnyFunSuite {

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

  test("Get links by valid account should return correct links") {
    val accountID = AccountsService.createAccount()
    AccountsService.link(accountID, "EmailService", 20_001)
    AccountsService.link(accountID, "PurchaseService", 30_001)
    val links = AccountsService.getLinksByAccount(accountID)
    assert(links == Map("EmailService" -> 20_001, "PurchaseService" -> 30_001))
  }

  test("Get links by invalid account should return empty map") {
    val invalidAccountID = UUID.randomUUID()
    val links = AccountsService.getLinksByAccount(invalidAccountID)
    assert(links.isEmpty)
  }
}