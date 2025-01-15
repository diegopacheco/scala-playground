package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite

class SafeEnvelopeTest extends AnyFunSuite {

  test("SafeEnvelope should correctly seal and unseal an envelope") {
    val envelope = new PlainEnvelope(Map("service1" -> "id1", "service2" -> "id2"))
    val sealedEnvelope = envelope.seal()
    val unsealedEnvelope = sealedEnvelope.open()

    assert(unsealedEnvelope.getID("service1").contains("id1"))
    assert(unsealedEnvelope.getID("service2").contains("id2"))
  }

  test("SafeEnvelope should correctly convert to and from sealed string") {
    val envelope = new PlainEnvelope(Map("service1" -> "id1", "service2" -> "id2"))
    val sealedEnvelope = envelope.seal()
    val sealedString = sealedEnvelope.toSealedString
    val newEnvelope = new SafeEnvelope(sealedString).open()

    assert(newEnvelope.getID("service1").contains("id1"))
    assert(newEnvelope.getID("service2").contains("id2"))
  }

  test("SafeEnvelope should handle empty envelopes") {
    val envelope = new PlainEnvelope()
    val sealedEnvelope = envelope.seal()
    val unsealedEnvelope = sealedEnvelope.open()

    assert(unsealedEnvelope.getID("service1").isEmpty)
  }
}