package com.github.diegopacheco.scalaplayground.typeclasses

object CanTalkMainApp extends App {
  
  final case class Person(name:String)
  final case class Cat(name:String)
  
  trait CanTalk[T]{
    def talk(talker:T): String
  }
  
  object AddOns{
    
    implicit object PersonTalker extends CanTalk[Person]{
     def talk(p:Person):String = s"Hi, I'm ${p.name}" 
    }
  
    implicit object CatTalker extends CanTalk[Cat]{
       def talk(c:Cat):String = s"burun burun burun (${c.name})"
    }
  
    implicit class TalkUtil[A](x:A){
       def talk(implicit talker:CanTalk[A]):String = talker.talk(x)  
    }
    
  }
  
  import AddOns._  
  println( Person("Diego").talk )
  println( Cat("Gandalhy").talk )
  
  final case class Dog(name:String)
  implicit object DogTalker extends CanTalk[Dog]{
    def talk(d:Dog):String = s"rof rof (${d.name})"
  }
  import DogTalker._
  
  println( Dog("Dunginha").talk ) 
  
}