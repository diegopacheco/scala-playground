package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite

class EncryptionServiceTest extends AnyFunSuite {

  test("encrypt should return a non-empty string") {
    val service = new EncryptionService("thisisaverysecurekey123456789012")
    val encrypted = service.encrypt("Hello, World!")
    assert(encrypted.nonEmpty)
  }

  test("decrypt should return the original string") {
    val service = new EncryptionService("thisisaverysecurekey123456789012")
    val original = "Hello, World!"
    val encrypted = service.encrypt(original)
    val decrypted = service.decrypt(encrypted)
    assert(decrypted == original)
  }

  test("encrypt and decrypt should work with empty string") {
    val service = new EncryptionService("thisisaverysecurekey123456789012")
    val original = ""
    val encrypted = service.encrypt(original)
    val decrypted = service.decrypt(encrypted)
    assert(decrypted == original)
  }

  test("encrypt and decrypt should work with special characters") {
    val service = new EncryptionService("thisisaverysecurekey123456789012")
    val original = "Special characters: !@#$%^&*()_+-=[]{}|;':\",./<>?"
    val encrypted = service.encrypt(original)
    val decrypted = service.decrypt(encrypted)
    assert(decrypted == original)
  }

  test("encrypt and decrypt should work with long strings") {
    val service = new EncryptionService("thisisaverysecurekey123456789012")
    val original = "a" * 1000
    val encrypted = service.encrypt(original)
    val decrypted = service.decrypt(encrypted)
    assert(decrypted == original)
  }
}