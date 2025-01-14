package com.github.diegopacheco.scala.ids.global

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class PurchaseServiceTest extends AnyFunSuite {

  val purchaseService = new PurchaseService(AccountsService)

  test("Purchase with valid account ID should return correct purchase history") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val purchases = purchaseService.purchase(accountID)
    assert(purchases == List((500, "PS5"), (300, "NintendoSwitch")))
  }

  test("Purchase with another valid account ID should return correct purchase history") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    val purchases = purchaseService.purchase(accountID)
    assert(purchases == List((1300, "iPhone16"), (900, "iWatch")))
  }

  test("Purchase with invalid account ID should return empty list") {
    val invalidAccountID = UUID.randomUUID()
    val purchases = purchaseService.purchase(invalidAccountID)
    assert(purchases.isEmpty)
  }
}