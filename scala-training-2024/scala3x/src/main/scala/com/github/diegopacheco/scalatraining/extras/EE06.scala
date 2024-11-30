package com.github.diegopacheco.scalatraining.extras

import org.json4s.scalap.Success

/*
 * Design a machine that sells tickets, check status of cards, and top up cards for a train station.
 */
object EE06:

  import scala.util.{Success, Failure, Try}

  class Card:
    private var balance = 70
    def checkBalance(): Int = balance
    def addBalance(amount: Int): Unit = balance += amount
    def deductBalance(amount: Int): Either[Boolean, Exception] =
      if balance >= amount then
        balance -= amount
        Left(true)
      else
        Right(new Exception("Insufficient balance: " + balance))

  class TicketMachine:
    private val ticketPrice = 2
    def buyTicket(card: Card): Either[Success[String], Exception] =
      card.deductBalance(ticketPrice) match
        case Left(_) => Left(Success("Ticket bought"))
        case Right(ex) => Right(ex)

  object TicketSystem:
    def topUp(card: Card, machine: TicketMachine): Either[Success[String], Exception] =
      machine.buyTicket(card)

object MainEE06 extends App:

  import EE06.*
  import EE06.TicketSystem.*

  val card = Card()
  val machine = TicketMachine()
  card.addBalance(10)
  topUp(card, machine)
  topUp(card, machine)
  println(card.checkBalance())
  card.addBalance(1)
