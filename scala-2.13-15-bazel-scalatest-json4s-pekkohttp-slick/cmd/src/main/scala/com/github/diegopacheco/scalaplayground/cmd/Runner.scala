package com.github.diegopacheco.scalaplayground.cmd

import com.github.scalaplayground.lib.Greeting
//import com.github.diegopacheco.scalaplayground.db.slick.Tables._

object Runner {
    def main(args: Array[String]): Unit = {
        val hello = Greeting.sayHello
        println(hello)
        println(Greeting.sayHelloJson)

        val server = PekkoHttpServer
        server.run()
    }
}