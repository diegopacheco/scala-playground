package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class PurchaseTests extends AnyFunSuite {
  val accountService: AccountsService.type = AccountsService
  val purchaseService = new PurchaseService(accountService)

  test("purchase should return true for a valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    assert(purchaseService.purchase(Account.fromUUID(accountID))!=null)
  }

  test("purchase should return false for an invalid account ID") {
    val accountID = UUID.randomUUID()
    assert(purchaseService.purchase(Account.fromUUID(accountID)) == List())
  }

  test("purchase should return true for another valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    assert(purchaseService.purchase(Account.fromUUID(accountID))!=null)
  }

  test("purchase should return false for another invalid account ID") {
    val accountID = UUID.randomUUID()
    assert(purchaseService.purchase(Account.fromUUID(accountID))==List())
  }
}