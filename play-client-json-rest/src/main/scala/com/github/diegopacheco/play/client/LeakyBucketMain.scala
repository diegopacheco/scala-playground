package com.github.diegopacheco.play.client

import java.util.Date
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import java.util.concurrent.CountDownLatch

object LeakyBucketMain extends App {
  
    class LeakyBucketBackpresurre(var rate:Int, var duration:FiniteDuration) {
      
        var numDropsInBucket:Int = 0
        var timeOfLastDropLeak:Date = null
        var _MS_BETWEEN_DROP_LEAKS = duration.toMillis
        
        def addDropToBucket():Boolean = {
            synchronized{
                var now = new Date()
                // first of all, let the bucket leak by the appropriate amount
                if (timeOfLastDropLeak != null) {
                    var deltaT = now.getTime() - timeOfLastDropLeak.getTime()
                    // note round down as part of integer arithmetic
                    var numberToLeak:Long = deltaT / _MS_BETWEEN_DROP_LEAKS
                    if (numberToLeak > 0) { //now go and do the leak
                        if (numDropsInBucket <= numberToLeak) {
                            numDropsInBucket = 0
                        } else {
                            numDropsInBucket -= numberToLeak.toInt
                        }
                        timeOfLastDropLeak = now
                    }
                }
                if (numDropsInBucket < rate) {
                    numDropsInBucket = numDropsInBucket + 1 
                    return true; // drop added
                }
                return false; // overflow
            }
        }
    }
    
    var bucketLimiter = new LeakyBucketBackpresurre(150, 1 second)
    
    var okCount = 0
    var koCount = 0
    val testSize = 200
    var latch:CountDownLatch = new CountDownLatch(testSize)
    
    for(i <- 1 to testSize){
       Future{
           if (bucketLimiter.addDropToBucket()) {
              print("OK, ")
              okCount += 1
          }else{
             print("KO, ")
             koCount += 1
          }
          latch.countDown()
       }
    }
    
    latch.await()
    println("DONE")
    println("OK : " + okCount)
    println("KO : " + koCount)
}