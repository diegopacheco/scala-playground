package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

class AccountsTests extends AnyFunSuite {

  test("createAccount should create a new account with a unique UUID") {
    val account = AccountsService.createAccount
    assert(account.getUUID != null)
    assert(AccountsService.getAllAccounts.contains(account.getUUID.toString))
  }

  test("getAllAccounts should return all accounts with their envelopes") {
    val accounts = AccountsService.getAllAccounts
    assert(accounts.nonEmpty)
    accounts.foreach { case (uuid, envelope) =>
      assert(UUID.fromString(uuid) != null)
      assert(envelope.isInstanceOf[PlainEnvelope])
    }
  }

  test("getAllAccounts should return correct service IDs in envelopes") {
    val accounts = AccountsService.getAllAccounts
    accounts.foreach { case (uuid, envelope) =>
      val services = envelope.asInstanceOf[PlainEnvelope].ids
      services.foreach { case (serviceName, serviceID) =>
        assert(serviceName.nonEmpty)
        assert(serviceID.nonEmpty)
      }
    }
  }
}