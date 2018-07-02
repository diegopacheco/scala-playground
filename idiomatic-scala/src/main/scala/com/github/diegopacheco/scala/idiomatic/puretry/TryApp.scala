package com.github.diegopacheco.scala.idiomatic.puretry

import scala.util.Try
import scala.util.Success
import scala.util.Failure

trait AccountService {
  type Amount = BigDecimal
  case class Balance(var amount: Amount = 0)
  case class Account(number: Int, var balance: Balance = Balance())

  def debit(a: Account, amount: Amount): Try[Account] = {
    if (a.balance.amount < amount)
      Failure(new Exception("Insufficient balance in account: " + a.balance.amount +  " and you required: " + amount))
    else
      Success(a.copy(balance = Balance(a.balance.amount - amount)))
  }

  def credit(a: Account, amount: Amount): Try[Account] =
    Success(a.copy(balance = Balance(a.balance.amount + amount)))
}
object AccountService extends AccountService

object TryApp extends App {

  import AccountService._

  val a = Account(1, Balance(100))
  val b = credit(a, 1000)
  println(b)

  val c = credit(a, 10)
  println(c)

  val result = for {
    b <- credit(a, 1000)
    c <- debit(b, 200)
    d <- debit(c, 901)
  } yield d
  println(result)
  
}