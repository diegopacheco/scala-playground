package com.github.diegopacheco.scala.playground2x.clazz

abstract class BaseTeam(name:String){
  def getName:String = name
  def getTeamSize:Int
}

class FootballTeam(name:String, size:Int) extends BaseTeam(name){
  override def getTeamSize: Int = size
}

object AbstractClazzApp extends App{

  val brazil = new FootballTeam("Brazil", 11)
  println(brazil.getName)
  println(brazil.getTeamSize)

}
