package com.github.diegopacheco.scala.ids.envelope

import org.scalatest.funsuite.AnyFunSuite

class PlainEnvelopeTest extends AnyFunSuite {

  test("PlainEnvelope should create a new envelope from given IDs") {
    val ids: ServiceIDPairs = Map("service1" -> "id1", "service2" -> "id2")
    val envelope = new PlainEnvelope().fromIDs(ids)

    assert(envelope.getID("service1").contains("id1"))
    assert(envelope.getID("service2").contains("id2"))
  }

  test("PlainEnvelope should add and retrieve IDs correctly") {
    val envelope = new PlainEnvelope()
    envelope.addID("service1", "id1")
    envelope.addID("service2", "id2")

    assert(envelope.getID("service1").contains("id1"))
    assert(envelope.getID("service2").contains("id2"))
  }

  test("PlainEnvelope should return None for non-existent service ID") {
    val envelope = new PlainEnvelope()
    assert(envelope.getID("nonExistentService").isEmpty)
  }

  test("PlainEnvelope should seal and unseal correctly") {
    val envelope = new PlainEnvelope(Map("service1" -> "id1", "service2" -> "id2"))
    val sealedEnvelope = envelope.seal()
    val unsealedEnvelope = sealedEnvelope.open()

    assert(unsealedEnvelope.getID("service1").contains("id1"))
    assert(unsealedEnvelope.getID("service2").contains("id2"))
  }

  test("PlainEnvelope should handle empty envelopes") {
    val envelope = new PlainEnvelope()
    val sealedEnvelope = envelope.seal()
    val unsealedEnvelope = sealedEnvelope.open()

    assert(unsealedEnvelope.getID("service1").isEmpty)
  }
}