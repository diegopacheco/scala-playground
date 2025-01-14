package com.github.diegopacheco.scala.ids.global

import java.util.UUID

trait LoginContract {
  def login(accountID: UUID, password: String): Boolean
}

class LoginService(val accountService:AccountsService.type) extends LoginContract {

  private val logins = Map(
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479") -> ("bananas",hash("bananas")),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480") -> ("apples",hash("apples")),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481") -> ("oranges",hash("oranges"))
  )

  override def login(accountID: UUID, password: String): Boolean = {
    logins.get(accountID) match {
      case Some((_, storedHash)) => storedHash == hash(password)
      case None => false
    }
  }

  private def hash(s: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-512")
    val digest = md.digest(s.getBytes)
    digest.map("%02x".format(_)).mkString
  }
}
