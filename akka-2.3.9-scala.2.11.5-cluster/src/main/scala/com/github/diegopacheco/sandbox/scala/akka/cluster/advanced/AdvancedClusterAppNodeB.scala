package com.github.diegopacheco.sandbox.scala.akka.cluster.advanced

import com.github.diegopacheco.sandbox.scala.akka.util.ClusterUtils

object AdvancedClusterAppNodeB{
  def main(args:Array[String]):Unit = {
     ClusterUtils.join2( args(0).toInt )
  }
}