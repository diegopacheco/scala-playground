package com.github.diegopacheco.scalaplayground.trick

import java.util.Date

object DateService {
  var ds = new Date
}

class DateService {
  def getDate: String = DateService.ds.toString
}
