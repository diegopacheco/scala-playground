package com.github.diegopacheco.scala.ids.global

import java.util.UUID

type Purchases = List[(Int,String)]

trait PurchaseContract {
  def purchase(accountID: UUID): Purchases
}

class PurchaseService(val accountsService:AccountsService.type ) extends PurchaseContract {

  // simulates reads from database, see emailID and loginid have no correlation with purchaseID.
  private val usersPurchaseHistory = Map(
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479") -> List((500, "PS5"),(300, "NintendoSwitch")),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480") -> List((1300, "iPhone16"),(900, "iWatch")),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481") -> List((5000, "4090Laptop"))
  )

  def purchase(accountID: UUID): Purchases = {
    usersPurchaseHistory.get(accountID) match {
      case Some(purchases) => purchases
      case None => List()
    }
  }
}
