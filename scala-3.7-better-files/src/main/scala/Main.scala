import better.files.*

@main def main(): Unit =
  val file = File("test.txt")

  file.write("Hello from Scala 3.7 with better-files!")
  println(s"Written to: ${file.path}")

  val content = file.contentAsString
  println(s"Content: $content")

  val dir = File(".")
  println(s"\nFiles in current directory:")
  dir.list.take(5).foreach(f => println(s"  ${f.name}"))

  file.delete()
  println(s"\nDeleted: ${file.path}")
