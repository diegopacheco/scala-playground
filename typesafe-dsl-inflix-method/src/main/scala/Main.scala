case class Time(hours: Int, minutes: Int):
  def +(other: Time): Time = 
    val totalMinutes = this.minutes + other.minutes + (this.hours + other.hours) * 60
    Time(totalMinutes / 60, totalMinutes % 60)

extension (n: Int)
  def hours: Time = Time(n, 0)
  def minutes: Time = Time(0, n)
  def hour: Time = Time(n, 0)
  def minute: Time = Time(0, n)

@main def main():Unit =
  val meeting = 2.hours + 30.minutes
  val break = 15.minutes
  val total = meeting + break
  println(total)

