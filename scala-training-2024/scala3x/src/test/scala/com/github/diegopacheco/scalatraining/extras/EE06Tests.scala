package com.github.diegopacheco.scalatraining.extras

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

class EE06Tests extends AnyFlatSpec with should.Matchers:
  "Test the Ticket System" should "work" in {
    import EE06.*
    import EE06.TicketSystem.*
    val card = Card()
    val machine = TicketMachine()
    card.addBalance(10)
    topUp(card, machine)
    topUp(card, machine)
    card.checkBalance() should be(76)
  }
