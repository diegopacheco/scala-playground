@main def hello(): Unit =
  botMenu(2)
  pay("NJ")

def botMenu(selection: Any):Unit =
  selection match
    case 1 => println("one - burguers and fries")
    case 2 => println("two - beverages")
    case _ => println("other - something else... ")

def pay(selection: Any): Double =
  val tax = selection match
    case 1    => 1.10
    case 2    => 3.45
    case "CA" => 11.78
    case "NJ" => 7.89
    case true => 12.00
    case _    => 15.00
  println(s"You need to pay tax ${tax}")
  tax