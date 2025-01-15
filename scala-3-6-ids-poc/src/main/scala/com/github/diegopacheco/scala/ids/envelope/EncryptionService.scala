package com.github.diegopacheco.scala.ids.envelope

import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import java.util.Base64

class EncryptionService(key: String) {
  private val charset = "UTF-8"
  private val algorithm = "AES/CBC/PKCS5Padding"
  private val keyBytes = key.getBytes(charset).take(32).padTo(32, 0.toByte) // Ensure the key is 256 bits (32 bytes)
  private val secretKey = new SecretKeySpec(keyBytes, "AES")
  private val iv = new Array[Byte](16) // 16 bytes IV for AES
  private val ivSpec = new IvParameterSpec(iv)

  def encrypt(data: String): String = {
    val cipher = Cipher.getInstance(algorithm)
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
    val encryptedBytes = cipher.doFinal(data.getBytes(charset))
    Base64.getEncoder.encodeToString(encryptedBytes)
  }

  def decrypt(encryptedData: String): String = {
    val cipher = Cipher.getInstance(algorithm)
    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
    val decodedBytes = Base64.getDecoder.decode(encryptedData)
    new String(cipher.doFinal(decodedBytes), charset)
  }
}

object EncryptionService {
  def apply(): EncryptionService = {
    val key = Option(System.getenv("ENC_KEY")).getOrElse("bananas")
    new EncryptionService(key)
  }
}
