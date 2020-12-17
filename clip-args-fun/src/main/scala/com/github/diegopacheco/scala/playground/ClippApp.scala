package com.github.diegopacheco.scala.playground

import io.github.vigoo.clipp.usageinfo.UsageInfo.PrintNode
import io.github.vigoo.clipp.usageinfo.{UsageInfo, UsageInfoExtractor}

object ClippApp extends App{

  import java.io.File
  import io.github.vigoo.clipp.parsers._
  import io.github.vigoo.clipp.syntax._

  case class Parameters1(inputUrl: String,
                         outputFile: File,
                         verbose: Boolean)

  val paramSpec1 = {
    for {
      _ <- metadata(programName = "app")

      inputUrl <- namedParameter[String]("URL to download", "url","input")
      outputFile <- namedParameter[File]("Target file", "file","output")
      verbose <- flag("Verbose output", 'v', "verbose")

    } yield Parameters1(inputUrl, outputFile, verbose)
  }

  val graph = UsageInfoExtractor.getUsageDescription(paramSpec1)
  val usageInfo = UsageInfo.generateUsageInfo(graph)

  println("App:")
  print(graph.metadata.get.programName)
  graph.resultGraph.toNodes.toArray.foreach( dp => println(dp.value) )

  println("UsageInfo:")
  usageInfo.toOption.toList.foreach( v => v.foreach( p => println( p.asInstanceOf[PrintNode].param.parameter)))
}
