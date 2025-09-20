
import CapitalCaseMacros.*

object CapitalCase {
    def runCapitalCase(): Unit = {
        type Hello = "Hello"
        type World = "World"

        val hello: Hello = capitalCase["hello"]
        val world: World = capitalCase["world"]
        println(hello)
        println(world)

        // Wont compile
        //val wrongHello: Hello = capitalCase["hellO"]
        //val wrongWorld: World = "world"
        //println(wrongHello)
        //println(wrongWorld)
    }
}
