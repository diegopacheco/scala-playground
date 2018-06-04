package com.github.diegopacheco.scala.scodec.simple

object Main extends App {
   
    import scodec.bits._

    val otp = hex"54686973206973206e6f74206120676f6f642070616420746f2075736521".bits

    val bits = hex"746be39ece241e0da28b7acd4fad63632249ec5e2e402d5a0b2cd95d0a05".bits

    val decoded = (bits ^ otp) rotateLeft 3

    println(decoded)
    
    import scodec.codecs._
    val msg = variableSizeBytes(uint16, utf8).decode(decoded).require.value
    
    println(msg)
}