@main def hello(): Unit =
  val dois = two
  println(dois)
  
  whatsYourTeam("Gremio")
  //whatsYourTeam("Palmeiras")
// [error] 6 |  whatsYourTeam("Palmeiras")
// [error]   |                ^^^^^^^^^^^
// [error]   |                Found:    ("Palmeiras" : String)
// [error]   |                Required: Gremio | Inter

  sayTheTruth(BestLanguage())

// literal type = 2 <- Int
val two:2 = 2

//
// | 
// 

type Gremio = "Gremio"
type Inter  = "Inter"
def whatsYourTeam(team: (Gremio | Inter)): Unit =
  println(s"your team is ${team}")

//
// &
//

trait Best:
  def sayIt(): String = "Scala 3"

trait Lang:
  def sayIt(): String = "Language"

class BestLanguage extends Best with Lang:
  override def sayIt(): String = "Scala 3 Language"

def sayTheTruth(theBest: (Best & Lang)): Unit =
  println(s"${theBest.sayIt()}")