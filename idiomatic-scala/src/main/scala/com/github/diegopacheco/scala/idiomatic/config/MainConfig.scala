package com.github.diegopacheco.scala.idiomatic.config

object MainConfig extends App {
  
    import com.typesafe.config.ConfigFactory
    
    val config = ConfigFactory.load()
    val awsSecret = config.getString("myconfig.aws.secret.value")
    val awsKey    = config.getString("myconfig.aws.key.value")
    
    println(s"My AWS secret is:[$awsSecret] and key: [$awsKey]")
  
}