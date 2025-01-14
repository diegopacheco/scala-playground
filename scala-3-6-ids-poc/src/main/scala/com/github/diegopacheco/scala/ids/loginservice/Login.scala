package com.github.diegopacheco.scala.ids.loginservice

trait LoginContract {
  def login(loginID: Int, password: String): Boolean
}

object LoginService extends LoginContract {
  private val users = Map(
    10_001 -> hash("banana"),
    10_002 -> hash("apple"),
    10_003 -> hash("orange")
  )

  override def login(loginID: Int, password: String): Boolean = {
    users.get(loginID) match {
      case Some(p) => p == password
      case None => false
    }
  }

  private def hash(s: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-512")
    val digest = md.digest(s.getBytes)
    digest.map("%02x".format(_)).mkString
  }
}
