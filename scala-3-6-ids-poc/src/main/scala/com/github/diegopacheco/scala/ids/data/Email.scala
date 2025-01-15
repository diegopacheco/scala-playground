package com.github.diegopacheco.scala.ids.data

import java.util.UUID

type Email = String

trait EmailContract {
  def getEmailByID(accountID:UUID): Email
}

class EmailService(val accountService:AccountsService.type) extends EmailContract {

  private val emails = Map(
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479") -> "bananas@gmail.com",
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480") -> "apples@gmail.com",
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481") -> "oranges@gmai.com"
  )

  override def getEmailByID(accountID:UUID): Email = {
    emails.get(accountID) match {
      case Some(email) => email
      case None => throw new RuntimeException("Email not found")
    }
  }

}