package com.github.diegopacheco.scala.ids.envelope

type ServiceIDPairs = Map[String,String]

/**
 * Handle envelope that are in plaintext.
 * Here is where we can add ids links and get ids links.
 * Once it's sealed you cannot do anything with it.
 */
trait Envelope {
  def addID(service: String, id: String): Boolean
  def getID(service: String): Option[String]
  def seal():SealedEnvelope
  def fromIDs(ids:ServiceIDPairs):Envelope
}

/**
 * Handle sealed envelope, it's a sealed envelope, you can only open it.
 * Once it's open you can get the ids links.
 *
 */
trait SealedEnvelope {
  def open(): Envelope
  def fromSealed(envelopeString: String): Envelope
  def toSealedString: String
}

/**
 * Future IMPL ideas:
 *
 * It's a simple envelope, it could have a header, even with body.
 * Versioning the envelope it would help for rotations, evolution and backward compatibility.
 * Example of a sealed envelope with 2 ids inside(44 bytes): Qv/tidy3+882j26ec8CvRJAhchi+ooklm1fmTP8KZPc=
 */
class PlainEnvelope(var ids:ServiceIDPairs = Map()) extends Envelope{

  private val es:EncryptionService = EncryptionService()

  override def addID(service: String, id: String): Boolean = {
    ids = ids + (service -> id)
    true
  }

  override def getID(service: String): Option[String] = {
    ids.get(service)
  }

  override def fromIDs(ids: ServiceIDPairs): Envelope = {
    val envelope = new PlainEnvelope(ids)
    envelope
  }

  override def seal(): SealedEnvelope = {
    val plainText = serialize()
    //println(s"Sealing: $plainText")
    val cipherText = es.encrypt(plainText)
    println(s"Sealed: $cipherText")
    new SafeEnvelope(cipherText)
  }

  private def serialize(): String = {
    ids.map {
      case (service, id) => s"$service:$id"
    }.mkString(",")
  }
}

class SafeEnvelope(val encEnvelope:String) extends SealedEnvelope {

  private val es: EncryptionService = EncryptionService()

  override def open(): Envelope = {
    val plainText = es.decrypt(encEnvelope)
    val ids = deserialize(plainText)
    val envelope = new PlainEnvelope(ids)
    envelope
  }

  override def fromSealed(encEnvelopeString: String): Envelope = {
    val decryptedString = es.decrypt(encEnvelopeString)
    val newIds = deserialize(decryptedString)
    val envelope = new PlainEnvelope(newIds)
    envelope
  }

  override def toSealedString: String = encEnvelope

  private def deserialize(plainText: String): ServiceIDPairs = {
    val ids = plainText.split(",").filter(_.nonEmpty).map { entry =>
      val Array(service, id) = entry.split(":")
      service -> id
    }.toMap
    ids
  }
}