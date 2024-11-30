package com.github.diegopacheco.scalatraining2x

/**
 * Implement a Loan Core Service that would receive the following parameters:
 *  - Annual Income
 *  - Amount to be borrowed
 *  The return might be a Loan with terms of the payment or maybe declined.
 *  Check that the user annual income is >= 95.245K at least and that today is not a holiday and that the user
 *  it not in a No Business List.
 */
object E05 {

  sealed trait Loan
  case class ApprovedLoan(terms: String) extends Loan
  case class DeclinedLoan(reason: String) extends Loan

  trait LoanService {
    def applyForLoan(annualIncome: Double, amount: Double): Loan
  }

  class LoanServiceImpl extends LoanService {
    override def applyForLoan(annualIncome: Double, amount: Double): Loan = {
      if (annualIncome >= 95245.0) {
        ApprovedLoan("Terms: 10% APR, 5 years")
      } else {
        DeclinedLoan("Annual income is too low")
      }
    }
  }

}

object E05App extends App {
  val loanService = new E05.LoanServiceImpl
  println(loanService.applyForLoan(100000.0, 10000.0))
  println(loanService.applyForLoan(90000.0, 10000.0))
}