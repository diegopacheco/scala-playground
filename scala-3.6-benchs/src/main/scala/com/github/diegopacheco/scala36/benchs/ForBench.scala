package com.github.diegopacheco.scala36.benchs

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(java.util.concurrent.TimeUnit.MILLISECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
class ForBench {

  @Benchmark
  def testFor(blackhole: Blackhole): Unit = {
    for (i <- 1 to 1000) {
      blackhole.consume(i)
    }
  }

  @Benchmark
  def lambdaForeach(blackhole: Blackhole): Unit = {
    (1 to 1000).foreach(blackhole.consume)
  }

  @Benchmark
  def testTailRec(blackhole: Blackhole): Unit = {
    @annotation.tailrec
    def loop(i: Int): Unit = {
      if (i <= 1000) {
        blackhole.consume(i)
        loop(i + 1)
      }
    }
    loop(1)
  }

}