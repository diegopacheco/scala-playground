package com.github.diegopacheco.scala.playground2x


object MathUtils {
  def double(i: Int) = i * 2
}

import org.scalatest.funspec.AnyFunSpec

class MathUtilsSpec extends AnyFunSpec {

  describe("MathUtils::double") {

    it("should handle 0 as input") {
      val result = MathUtils.double(0)
      assert(result == 0)
    }

    it("should handle 1") {
      val result = MathUtils.double(1)
      assert(result == 2)
    }

    it("should handle really large integers") (pending)

  }

}