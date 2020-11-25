package com.github.diegopacheco.sccalaplayground.monix

object ConsumerApp extends App{

  import monix.eval._
  import monix.reactive._
  implicit val scheduler = monix.execution.Scheduler.global

  // A consumer that folds over the elements of the stream,
  // producing a sum as a result
  val sumConsumer = Consumer.foldLeft[Long,Long](0L)(_ + _)

  // For processing sums in parallel, useless of course, but can become
  // really helpful for logic sprinkled with I/O bound stuff
  val loadBalancer = {
    Consumer
      .loadBalance(parallelism=10, sumConsumer)
      .map(_.sum)
  }

  val observable: Observable[Long] = Observable.range(0, 100000)
  // Our consumer turns our observable into a Task processing sums, w00t!
  val task: Task[Long] = observable.consumeWith(loadBalancer)

  // Consume the whole stream and get the result
  task.runToFuture.foreach(println)
  //=> 4999950000

  Thread.sleep(1000L)
}
