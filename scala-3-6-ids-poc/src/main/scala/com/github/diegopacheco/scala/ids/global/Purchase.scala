package com.github.diegopacheco.scala.ids.global

type Purchases = List[(Int,String)]

trait PurchaseContract {
  def purchase(purchaseUserID: Int): Purchases
}

object PurchaseService extends PurchaseContract {

  // simulates reads from database, see emailID and loginid have no correlation with purchaseID.
  private val usersPurchaseHistory = Map(
    30_001 -> List((500, "PS5"),(300, "NintendoSwitch")),
    30_002 -> List((1300, "iPhone16"),(900, "iWatch")),
    30_003 -> List((5000, "4090Laptop"))
  )

  def purchase(purchaseUserID: Int): Purchases = {
    usersPurchaseHistory.get(purchaseUserID) match {
      case Some(p) => p
      case None => List.empty
    }
  }
}
