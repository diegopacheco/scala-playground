package com.github.diegopacheco.scalaplayground.loan

final class Income private(val value: Int) extends AnyVal{
  override def toString: String = s"Income($value)"
}

object Income {
  def apply(value: Int): Option[Income] = {
    if (value >= 100000) Some(new Income(value))
    else None
  }
}

sealed trait LoanState
case class Applied(income: Income) extends LoanState
case class Approved(income: Income) extends LoanState
case class Rejected(income: Income) extends LoanState

object Loan {
  def apply(income: Int): Option[LoanState] = {
    Income(income).map(Applied.apply)
  }
  def approve(loan: Applied): Approved = Approved(loan.income)
  def reject(loan: Applied): Rejected = Rejected(loan.income)
}

object LoanTypeSystemApp extends App:
  val loan = Loan(110000)
  loan match {
    case Some(applied: Applied) => println(Loan.approve(applied))
    case _                       => println("Loan rejected")
  }
