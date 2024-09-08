
object App extends App{

  import literals.port

  val p: Port = port"8080"
  println(p)

  //val q: Port = port"100000" // invalid port - must be integer between 0 and 65535 - val q: Port = port"100000"

}
