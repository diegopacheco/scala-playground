@main def hello(): Unit =
  val i = 32
  val N = 42
  i match
   case 0 => println("1")
   case 1 => println("2")
   case N => println("42")
   case what => println(s"You gave me: $what")
  
  val x:Int  = 2
  val evenOrOdd = x match
    case 1 | 3 | 5 | 7 | 9 => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
    case _ => println("some other number")

  4 match
    case 1 => println("one, a lonely number")
    case x if x == 2 || x == 3 => println("two’s company, three’s a crowd")
    case x if x > 3 => println("4+, that’s a party")
    case _ => println("i’m guessing your number is zero or less")

  27 match
    case a if 0 to 9 contains a => println(s"0-9 range: $a")
    case b if 10 to 19 contains b => println(s"10-19 range: $b")
    case c if 20 to 29 contains c => println(s"20-29 range: $c")
    case _ => println("Hmmm...")

  case class Person(name: String)
  
  def speak(p: Person) = p match
    case Person(name) if name == "Fred" => println(s"$name says, Yubba dubba doo")
    case Person(name) if name == "Bam Bam" => println(s"$name says, Bam bam!")
    case _ => println("Watch the Flintstones!")

  speak(Person("Fred"))      // "Fred says, Yubba dubba doo"
  speak(Person("Bam Bam"))   // "Bam Bam says, Bam bam!"  

  def isTruthy(a: Matchable) = a match
    case 0 | "" | false => false
    case _              => true

  println(isTruthy(0))