package com.github.diegopacheco.scala3.playground.features

@main def FunctionMatchApp():Unit = {
  
  def createGreetingFunction(desiredLanguage:String): String => Unit =
    val englishGreeting = (name: String) => println(s"Hello, $name")
    val frenchGreeting = (name: String) => println(s"Bonjour, $name")
    desiredLanguage match
      case "english" => englishGreeting
      case "french" => frenchGreeting
  
  val result = createGreetingFunction("english");
  println(result.apply("Diego"))
  
} 

