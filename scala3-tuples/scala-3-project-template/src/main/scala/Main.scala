case class Actor(name: String)

@main def hello(): Unit =
  val strangerThings = (11, "eleven", Actor("Eleven"))
  println(strangerThings)
  println(strangerThings(0))
  println(strangerThings(1))
  println(strangerThings(2))

  val (id, str, actor) = strangerThings
  println(s"id: ${id}, string: ${str}, Actor ${actor}")