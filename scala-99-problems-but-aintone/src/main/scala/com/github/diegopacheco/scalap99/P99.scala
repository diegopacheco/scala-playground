package com.github.diegopacheco.scalap99

// P99 (***) Crossword puzzle.
//
// Given an empty (or almost empty) framework of a crossword puzzle and a set of words. The problem is to place the words 
// into the framework.
//
//The particular crossword puzzle is specified in a text file which first lists the words (one word per line) in an arbitrary 
// order. Then, after an empty line, the crossword framework is defined. In this framework specification, an empty character 
// location is represented by a dot (.). In order to make the solution easier, character locations can also contain predefined 
// character values. The puzzle opposite is defined in the file p99a.dat, other examples are p99b.dat and p99d.dat. There is also
// an example of a puzzle (p99c.dat) which does not have a solution.
//
//Words are strings of at least two characters. A horizontal or vertical sequence of character places in the crossword puzzle
// framework is called a site. Our problem is to find a compatible way of placing words onto sites.
//
//Hints:
//The problem is not easy. You will need some time to thoroughly understand it. So, don’t give up too early! And 
// remember that the objective is a clean solution, not just a quick-and-dirty hack!
//
//For efficiency reasons it is important, at least for larger puzzles, to sort the words and the sites in a particular
// order. For this part of the problem, the solution of P28 may be very helpful.
//

import java.io.File
import scala.io.Source


object P99 {
  case class Crossword(words: List[String], framework: Array[Array[Char]])

  def parseInput(filePath: String): Crossword = {
    val source = Source.fromFile(new File(".").getCanonicalPath + "/src/main/resources/" + filePath)
    val lines = source.getLines().toList
    source.close()
    val (wordLines, frameworkLines) = lines.span(_.nonEmpty)
    val words = wordLines.filter(_.nonEmpty)
    val framework = frameworkLines.drop(1).map(_.toArray).toArray
    Crossword(words, framework)
  }

  def findSites(framework: Array[Array[Char]]): List[List[(Int, Int)]] = {
    val rows = framework.length
    val cols = framework.map(_.length).max
    val sites = scala.collection.mutable.ListBuffer[List[(Int, Int)]]()

    // Find horizontal sites
    for (i <- 0 until rows) {
      var site = List[(Int, Int)]()
      for (j <- 0 until cols) {
        if (j < framework(i).length && framework(i)(j) == '.') {
          site = site :+ (i, j)
        } else if (site.length > 1) {
          sites += site
          site = List()
        } else {
          site = List()
        }
      }
      if (site.length > 1) sites += site
    }

    // Find vertical sites
    for (j <- 0 until cols) {
      var site = List[(Int, Int)]()
      for (i <- 0 until rows) {
        if (j < framework(i).length && framework(i)(j) == '.') {
          site = site :+ (i, j)
        } else if (site.length > 1) {
          sites += site
          site = List()
        } else {
          site = List()
        }
      }
      if (site.length > 1) sites += site
    }

    sites.toList
  }

  def isValidPlacement(framework: Array[Array[Char]], site: List[(Int, Int)], word: String): Boolean = {
    site.zip(word).forall { case ((i, j), c) =>
      framework(i)(j) == '.' || framework(i)(j) == c
    }
  }

  def placeWord(framework: Array[Array[Char]], site: List[(Int, Int)], word: String): Unit = {
    site.zip(word).foreach { case ((i, j), c) =>
      framework(i)(j) = c
    }
  }

  def removeWord(framework: Array[Array[Char]], site: List[(Int, Int)]): Unit = {
    site.foreach { case (i, j) =>
      framework(i)(j) = '.'
    }
  }

  def solveCrossword(crossword: Crossword): Boolean = {
    val sites = findSites(crossword.framework)
    def solve(words: List[String], sites: List[List[(Int, Int)]]): Boolean = {
      if (words.isEmpty) return true
      val word = words.head
      for (site <- sites) {
        if (site.length == word.length && isValidPlacement(crossword.framework, site, word)) {
          placeWord(crossword.framework, site, word)
          if (solve(words.tail, sites.filterNot(_ == site))) return true
          removeWord(crossword.framework, site)
        }
      }
      false
    }
    solve(crossword.words, sites)
  }

  def printFramework(framework: Array[Array[Char]]): Unit = {
    framework.foreach { row =>
      println(row.mkString(" "))
    }
  }
}

/*
 * P99 Crossword puzzle solved:
 *
 * P R O L O G     E
 * E   N     N     M
 * R   L I N U X   A
 * L   I   F   M A C
 *     N   S Q L   S
 *   W E B
 *
 */
object P99Main extends App {
  import P99._

  val crossword = parseInput("99a.dat")
  if (solveCrossword(crossword)) {
    printFramework(crossword.framework)
  } else {
    println("No solution exists")
  }
}