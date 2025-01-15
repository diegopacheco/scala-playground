package com.github.diegopacheco.scala.ids.data

import org.scalatest.funsuite.AnyFunSuite
import com.github.diegopacheco.scala.ids.envelope._

class AccountsServiceTest extends AnyFunSuite {

  test("Create account should return a valid AccountID") {
    val accountID = AccountsService.createAccount
    assert(accountID.isInstanceOf[AccountID])
  }

  test("Get all accounts should return a map of accounts") {
    val accounts = AccountsService.getAllAccounts
    assert(accounts.isInstanceOf[Map[String, Envelope]])
  }

  test("Create account should add a new account to the accounts map") {
    val initialSize = AccountsService.getAllAccounts.size
    AccountsService.createAccount
    val newSize = AccountsService.getAllAccounts.size
    assert(newSize == initialSize + 1)
  }

  test("Get all accounts should return correct envelopes") {
    val accountID = AccountsService.createAccount
    val accounts = AccountsService.getAllAccounts
    assert(accounts.contains(accountID.getUUID.toString))
    assert(accounts(accountID.getUUID.toString).isInstanceOf[Envelope])
  }
}
