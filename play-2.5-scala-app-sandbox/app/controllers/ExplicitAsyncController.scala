package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.duration._
import scala.util.Failure
import scala.util.Success

@Singleton
class ExplicitAsyncController @Inject() extends Controller {

  def index = Action.async {
    val futureInt = scala.concurrent.Future { intensiveComputation() }
    futureInt.map(i => Ok("Got result: " + i.onComplete {
      case Success(i) => Logger.info((s"got my $i"))
      case Failure(ex) => Logger.error(("This is the error: " + ex))
    }))
  }

  def timeout = Action.async {
    
    val futureInt = scala.concurrent.Future { 
          if (scala.util.Random.nextInt(2) == 0){ 
            100 
          }else{ 
            Thread.sleep(2000); 
            0
         }
    }
    val timeoutFuture = play.api.libs.concurrent.Promise.timeout("Oops", 1.second)
    
    scala.concurrent.Future.firstCompletedOf(Seq(futureInt, timeoutFuture)).map {
      case i:Int => Ok("Got result: " + i)
      case t:String => InternalServerError(t)
    }
    
  }

  def intensiveComputation(): scala.concurrent.Future[Int] = {
    scala.concurrent.Future {
      Thread.sleep(2000)
      val result = 10 + 10 + 30 + 10
      Logger.info("Async Computation Result: " + result)
      result
    }
  }

}