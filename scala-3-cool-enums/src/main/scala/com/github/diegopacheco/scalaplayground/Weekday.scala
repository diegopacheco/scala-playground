package com.github.diegopacheco.scalaplayground

enum Weekday(val index: Int) {
  private def next(i: Int) = (i + 1) % 7
  private def prev(i: Int) = (i + 7) - 1 % 7

  def nextDay: Weekday = Weekday.fromOrdinal(next(index))
  def prevDay: Weekday = Weekday.fromOrdinal(prev(index))

  case Mon   extends Weekday(0)
  case Tue   extends Weekday(1)
  case Wed   extends Weekday(2)
  case Thu   extends Weekday(3)
  case Fri   extends Weekday(4)
  case Sat   extends Weekday(5)
  case Sun   extends Weekday(6)
}
