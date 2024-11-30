package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._

class E05Tests extends AnyFlatSpec with should.Matchers {

  "Loan that is approved" should "be" in {
    val loanService = new E05.LoanServiceImpl
    val l1 = loanService.applyForLoan(100000.0, 10000.0)
    l1.toString should be("ApprovedLoan(Terms: 10% APR, 5 years)")
  }

  "Loan that is declined" should "be" in {
    val loanService = new E05.LoanServiceImpl
    val l2 = loanService.applyForLoan(90000.0, 10000.0)
    l2.toString should be("DeclinedLoan(Annual income is too low)")
  }

}