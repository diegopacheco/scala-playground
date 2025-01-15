package com.github.diegopacheco.scala.ids.data

import java.util.UUID

trait AccountsContract {
  def createAccount(): UUID
  def link(accountID:UUID, service:String, serviceID: Int): Boolean
  def getLinksByAccount(accountID:UUID): Map[String, Int]
}

object AccountsService extends AccountsContract {
  private var accounts: Map[UUID, Map[String, Int]] = Map(
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479") -> Map("loginService" -> 10_001, "EmailService" -> 20_001, "PurchaseService" -> 30_001),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480") -> Map("loginService" -> 10_002, "EmailService" -> 20_002, "PurchaseService" -> 30_002),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481") -> Map("loginService" -> 10_003, "EmailService" -> 20_003, "PurchaseService" -> 30_003)
  )

  def createAccount(): UUID = {
    val accountID = UUID.randomUUID()
    val account:(UUID, Map[String, Int]) = accountID -> Map()
    accounts = accounts + account
    BusService.publish("accountCreated",account)
    accountID
  }

  def link(accountID: UUID, service:String,serviceID: Int): Boolean = {
    accounts.get(accountID) match {
      case Some(account) =>
        val link = (accountID -> (account + (service -> serviceID)))
        accounts = accounts + link
        BusService.publish("accountLinked",link)
        true
      case None => false
    }
  }

  override def getLinksByAccount(accountID: UUID): Map[Email, Int] = {
    accounts.get(accountID) match {
      case Some(account) => account
      case None => Map()
    }
  }
}
