package com.github.diegopacheco.scala.idiomatic.algebra.forcomprehension

case class User(name: String, contactInformation: Option[ContactInformation])
case class ContactInformation(email: Option[Email])
case class Email(address: String, verified: Boolean)

object User {
  def byId(id: Int): Option[User] = {
      if (id==1) 
        Option(User("Diego",Option(ContactInformation(Option(Email("my@mail.com",true)))))) 
      else 
        None
  }
}

object SimpleUser {
  def emailInformation(id: Int) = for {
    user <- User.byId(id)
    contactInformation <- user.contactInformation
    email <- contactInformation.email if email.verified
  } yield (user.name, email.address)
}

object ForComprehensionMain extends App {
  
  println( SimpleUser.emailInformation(1) )
  
}