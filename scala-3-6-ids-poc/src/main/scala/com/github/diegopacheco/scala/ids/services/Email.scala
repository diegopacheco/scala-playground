package com.github.diegopacheco.scala.ids.services

type Email = String

trait EmailContract {
  def getEmailByID(emailID:Int): Email
}

object EmailService extends EmailContract {

  // simulates reads from database, see emailID and loginid have no correlation.
  private val emails = Map(
    20_001 -> "bananas@gmail.com",
    20_002 -> "apples@gmail.com",
    20_003 -> "oranges@gmai.com"
  )

  override def getEmailByID(emailID: Int): Email = {
    emails.get(emailID) match {
      case Some(e) => e
      case None => ""
    }
  }

}