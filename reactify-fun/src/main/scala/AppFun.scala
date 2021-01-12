object AppFun extends App{

  import reactify._

  val myChannel = Channel[String]           // Creates a Channel that receives Strings
  val myVar = Var[Int](5)                   // Creates a Var containing the explicit value `5`
  val myVal = Val[Int](myVar + 5)           // Create a Val containing the sum of `myVar` + `5`

  myVal.attach { newValue =>
    println(s"aha! myVal = $newValue")
  }
  myVar := 10

  val v1 = Var(11)
  val v2 = Var("Yes")
  val v3 = Var("No")
  val complex = Val[String] {
    if (v1 > 10) {
      v2
    } else {
      v3
    }
  }
  println(complex)

  implicit val s2i: String => Int = (s: String) => Integer.parseInt(s)
  implicit val i2s: Int => String = (i: Int) => i.toString

  val a = Var[String]("5")
  val b = Var[Int](10)
  val binding = a bind b
  binding.detach()
  println(binding)
}
