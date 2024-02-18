def parseDouble(s: String) = try { Some(s.toDouble) } catch { case _ => None }

object Conversions:
  given stringToDouble: Conversion[String, Double] with
    def apply(s: String): Double = parseDouble(s).get

@main def hello(): Unit =
  import Conversions.stringToDouble

  val value:Double = "22.4"
  println(value)
