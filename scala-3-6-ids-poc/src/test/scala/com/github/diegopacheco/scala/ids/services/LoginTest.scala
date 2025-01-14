package com.github.diegopacheco.scala.ids.services

import org.scalatest.funsuite.AnyFunSuite

class LoginTest extends AnyFunSuite {

  test("Login with correct credentials should return true") {
    assert(LoginService.login(10_001, hash("banana")))
    assert(LoginService.login(10_002, hash("apple")))
    assert(LoginService.login(10_003, hash("orange")))
  }

  test("Login with incorrect credentials should return false") {
    assert(!LoginService.login(10_001, hash("wrongpassword")))
    assert(!LoginService.login(10_004, hash("banana")))
  }

  private def hash(s: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-512")
    val digest = md.digest(s.getBytes)
    digest.map("%02x".format(_)).mkString
  }
}