package com.github.diegopacheco.play.client

import scala.concurrent.Future
import com.google.common.util.concurrent.RateLimiter

// blocks -> http://stackoverflow.com/questions/1407113/throttling-method-calls-to-m-requests-in-n-seconds
object MainBackPressure extends App {
    
    def printy(i:Int) = {
      throttle.acquire(1)
      println("i: "+ i)
    }
  
    import scala.concurrent.ExecutionContext.Implicits.global
    val throttle:RateLimiter = RateLimiter.create(1)
    
    for( i <- 1 to 10000 ){
      Future {
          try{
               Thread.sleep(5000)
               printy(i)
          }catch{
            case e:Throwable => println(e)
          }
      }
    }
    
    Thread.sleep(20000)
    println("DONE")
}