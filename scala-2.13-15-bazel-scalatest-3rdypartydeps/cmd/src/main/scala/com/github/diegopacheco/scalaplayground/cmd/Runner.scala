package com.github.diegopacheco.scalaplayground.cmd

import com.github.scalaplayground.lib.Greeting

object Runner {
    def main(args: Array[String]): Unit = {
        val hello = Greeting.sayHello
        println(hello)
        println(Greeting.sayHelloJson)
    }
}