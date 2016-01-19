package com.gthub.diegopacheco.scala.playground.test

import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MockingSpecTest extends FlatSpec with Matchers with MockFactory {
    
  "A Mock" should "return 42 no matter what" in {
    
    val m = mockFunction[Int, String]
    m expects (42) returning "Forty two" once
    
    m.apply(42) should be ("Forty two")
    
  }    
  
}