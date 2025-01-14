package com.github.diegopacheco.scala.ids.services

import org.scalatest.funsuite.AnyFunSuite

class PurchaseTest extends AnyFunSuite {

  test("Purchase with valid user ID should return correct purchase history") {
    assert(PurchaseService.purchase(30_001) == List((500, "PS5"), (300, "NintendoSwitch")))
    assert(PurchaseService.purchase(30_002) == List((1300, "iPhone16"), (900, "iWatch")))
    assert(PurchaseService.purchase(30_003) == List((5000, "4090Laptop")))
  }

  test("Purchase with invalid user ID should return empty list") {
    assert(PurchaseService.purchase(30_004).isEmpty)
  }
}