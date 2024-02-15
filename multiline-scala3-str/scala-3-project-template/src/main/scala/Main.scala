@main def hello(): Unit =
  val color:String = "blue"
  val html: String = 
  s"""
    <html>
    <body>
      <div style="bg-color: ${color}" />
    </body>
    </html>
  """
  println(html)