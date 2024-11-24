package com.github.scalaplayground.lib

import org.json4s.native.Serialization

object Greeting extends Json4sSupport {
    def sayHello:String = {
        val result = "Hello Scala 2.13.15 and Bazel!"
        result
    }
    def sayHelloJson:String = {
        val json = Serialization.write(sayHello)(formats)
        json
    }
}
