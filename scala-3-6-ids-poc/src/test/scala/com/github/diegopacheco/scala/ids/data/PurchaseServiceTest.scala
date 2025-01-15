package com.github.diegopacheco.scala.ids.data

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class PurchaseServiceTest extends AnyFunSuite {

  val purchaseService = new PurchaseService(AccountsService)

  test("makePurchase should return true for a valid purchase") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val result = purchaseService.purchase(accountID)
    assert(null!=result)
  }

  test("makePurchase should return List() for an invalid account ID") {
    val invalidAccountID = UUID.randomUUID()
    val result = purchaseService.purchase(invalidAccountID)
    assert(result==List())
  }

  test("makePurchase should return right purchase for a valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val result = purchaseService.purchase(accountID)
    assert(result==List((500,"PS5"), (300,"NintendoSwitch")))
  }
}