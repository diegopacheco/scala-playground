@main def hello(): Unit =
  import StringUtils._
  println(isNullOrEmpty(null))           // true
  println(isNullOrEmpty("Scala3"))       // false
  println(leftTrim("   Scala3"))         // Scala3
  println(rightTrim("Scala3   "))        // Scala3  

object StringUtils:
  def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty
  def leftTrim(s: String): String = s.replaceAll("^\\s+", "")
  def rightTrim(s: String): String = s.replaceAll("\\s+$", "")