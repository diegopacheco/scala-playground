package com.github.diegopacheco.scala35.pekko

import com.typesafe.config.ConfigFactory
import org.apache.pekko.actor.typed.{ActorSystem, Behavior}
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.slf4j.LoggerFactory

object App {
  private val logger = LoggerFactory.getLogger(this.getClass)

  object RootBehavior {
    def apply(): Behavior[Nothing] = Behaviors.setup[Nothing] { context =>
      // Create an actor that handles cluster domain events
      context.spawn(ClusterListener(), "ClusterListener")

      Behaviors.empty
    }
  }

  def main(args: Array[String]): Unit = {
    logger.info("Application starting...")
    val ports =
      if (args.isEmpty)
        Seq(17356, 17357, 0)
      else
        args.toSeq.map(_.toInt)
    ports.foreach(startup)
  }

  def startup(port: Int): Unit = {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(s"""
      pekko.remote.artery.canonical.port=$port
      """).withFallback(ConfigFactory.load())

    // Create an Apache Pekko system
    ActorSystem[Nothing](RootBehavior(), "ClusterSystem", config)
  }

}