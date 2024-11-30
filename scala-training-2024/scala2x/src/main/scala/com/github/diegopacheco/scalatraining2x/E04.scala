package com.github.diegopacheco.scalatraining2x

/**
 * Build a relational algebra type system consider that a loan has the following stages:
 * Applied, Verified, RequestMoreDocs,Approved of Denied.
 */
object E04 {
  sealed trait Loan
  case class Applied() extends Loan
  case class Verified(l:Applied) extends Loan
  case class RequestMoreDocs(l:Loan) extends Loan
  case class Approved(l:Verified) extends Loan
  case class Denied(l:Verified) extends Loan

  def applyLoan(): Applied = Applied()
  def verify(loan: Applied): Verified = Verified(loan)
  def requestMoreDocs(loan: Loan): RequestMoreDocs = RequestMoreDocs(loan)
  def approve(loan: Verified): Approved = Approved(loan)
  def deny(loan: Verified): Denied = Denied(loan)
}

object E04App extends App {
  import E04._

  val l1 = applyLoan()
  val l1Ver = verify(l1)
  val l1_docs = requestMoreDocs(l1Ver)
  val l1Approved = approve(l1Ver)
  val l1No = deny(l1Ver)
  println(s"Loan 1: ${l1} - ${l1Ver} - ${l1_docs} - ${l1Approved} - ${l1No}")

}