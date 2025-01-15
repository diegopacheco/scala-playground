package com.github.diegopacheco.scala.ids.data

import org.scalatest.funsuite.AnyFunSuite
import org.json4s.native.Serialization.write
import org.json4s.native.Serialization.read

case class TestData(name: String, value: Int)

class JsonSupportTest extends AnyFunSuite with JsonSupport {

  test("serialize should convert an object to JSON string") {
    val data = TestData("test", 42)
    val jsonString = write(data)
    assert(jsonString == """{"name":"test","value":42}""")
  }

  test("deserialize should convert a JSON string to an object") {
    val jsonString = """{"name":"test","value":42}"""
    val data = read[TestData](jsonString)
    assert(data == TestData("test", 42))
  }

  test("serialize and deserialize should be symmetric") {
    val data = TestData("test", 42)
    val jsonString = write(data)
    val deserializedData = read[TestData](jsonString)
    assert(deserializedData == data)
  }
}
