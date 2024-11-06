package com.github.diegopacheco.scalapatterns.validation

case class User(username: String, email: String, password: String)

sealed trait ValidationError {
  def message: String
}

case object InvalidUsername extends ValidationError {
  def message: String = "Username must be at least 3 characters long."
}

case object InvalidEmail extends ValidationError {
  def message: String = "Email is not valid."
}

case object InvalidPassword extends ValidationError {
  def message: String = "Password must be at least 8 characters long."
}

object UserValidator {
  type ValidationResult[A] = Either[ValidationError, A]

  def validateUsername(username: String): ValidationResult[String] = {
    if (username.length >= 3) Right(username)
    else Left(InvalidUsername)
  }

  def validateEmail(email: String): ValidationResult[String] = {
    if (email.contains("@")) Right(email)
    else Left(InvalidEmail)
  }

  def validatePassword(password: String): ValidationResult[String] = {
    if (password.length >= 8) Right(password)
    else Left(InvalidPassword)
  }
}

object UserRegistration {
  import UserValidator._

  def validateUser(username: String, email: String, password: String): List[ValidationError] = {
    val validations = List(
      validateUsername(username),
      validateEmail(email),
      validatePassword(password)
    )
    validations.collect { case Left(error) => error }
  }

  def registerUser(username: String, email: String, password: String): Either[List[ValidationError], User] = {
    val errors = validateUser(username, email, password)
    if (errors.isEmpty) Right(User(username, email, password))
    else Left(errors)
  }
}

object ValidationApp extends App{
  import UserRegistration._

  val result = registerUser("john", "john@example.com", "password123")
  result match {
    case Right(user) => println(s"User registered: $user")
    case Left(errors) => errors.foreach(error => println(s"Error: ${error.message}"))
  }
}
