package com.github.diegopacheco.scalatraining2x

import org.scalatest._
import flatspec._
import matchers._

class E04Tests extends AnyFlatSpec with should.Matchers {

  "Loan" should "be" in {
    val l1 = E04.applyLoan()
    val l1Ver = E04.verify(l1)
    val l1_docs = E04.requestMoreDocs(l1Ver)
    val l1Approved = E04.approve(l1Ver)
    val l1No = E04.deny(l1Ver)
    l1.toString should be("Applied()")
    l1Ver.toString should be("Verified(Applied())")
    l1_docs.toString should be("RequestMoreDocs(Verified(Applied()))")
    l1Approved.toString should be("Approved(Verified(Applied()))")
    l1No.toString should be("Denied(Verified(Applied()))")
  }

}

