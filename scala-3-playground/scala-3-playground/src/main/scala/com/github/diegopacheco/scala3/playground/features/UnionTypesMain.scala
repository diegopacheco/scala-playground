package com.github.diegopacheco.scala3.playground.features

import scala.util.hashing.Hashing

object UnionTypesMain extends App{

  case class UserName(name:String)
  case class Password(hash:Hashing[String])
  
  def help(id:UserName | Password) =
    val user = id match {
      case UserName(name) => println("username route"); 1
      case Password(hash)  => println("Password route"); 2
      case _ => println("IDK whats going on. Problem!"); -1;  
    }
    user
  
  // help("hi") // Found: ("hi" : String) Required: UnionTypesMain.this.UserName | UnionTypesMain.this.Password
  
  val id = help(UserName("diegopacheco"))
  println(s"ID is ${id}")

  // Tagged Unions with separated enum is another option
  enum UsernameOrPassword:
    case IsUsername(u: UserName)
    case IsPassword(p: Password)
  
  import UsernameOrPassword._
  
  def help2(up:UsernameOrPassword):Int = {
    up match{
      case IsUsername(u) => println(s"Username is ${u}")
      case IsPassword(p) => println(s"Password is ${p}")  
    }
    1
  }

  val h2:IsUsername | IsPassword = IsUsername(UserName("Diego"))
  help2(h2)
  
}
