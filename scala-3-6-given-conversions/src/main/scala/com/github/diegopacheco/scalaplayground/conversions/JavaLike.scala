package com.github.diegopacheco.scalaplayground.conversions

import java.util.Optional

object JavaLike {
  def addOne(optional: Optional[Int]): Optional[Int] = {
    if (optional.isPresent) {
      Optional.of(optional.get + 1)
    } else {
      Optional.empty()
    }
  }
}