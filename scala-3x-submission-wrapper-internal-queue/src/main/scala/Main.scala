import com.github.diegopacheco.scala.playground.task.TaskService

@main def main():Unit =
  val ts:TaskService = TaskService()

  ts.submitTask( () => println("1. Hello from WorkTask!") )

  ts.submitTask( () => {
    println("2. does not gonna go well...")
    Thread.sleep(3000)
    println("2. Done?")
  } )
