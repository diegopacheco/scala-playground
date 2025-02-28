package com.github.diegopacheco.scala.ids.services

import java.util.UUID

trait AccountsContract {
  def createAccount(): UUID
  def link(accountID:UUID, service:String, serviceID: Int): Boolean
  def getLinksByAccount(accountID:UUID): Map[String, Int]
}

object AccountsService extends AccountsContract {
  private var accounts: Map[UUID, Map[String, Int]] = Map()

  def createAccount(): UUID = {
    val accountID = UUID.randomUUID()
    accounts = accounts + (accountID -> Map())
    accountID
  }

  def link(accountID: UUID, service:String,serviceID: Int): Boolean = {
    accounts.get(accountID) match {
      case Some(account) =>
        accounts = accounts + (accountID -> (account + (service -> serviceID)))
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
