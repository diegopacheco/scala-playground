package com.github.diegopacheco.scalaplayground.di

/*
 * Explanation of why the code follows the Cake Pattern:
 *
 * Component Traits: Defined UserRepositoryComponent and
 * UserServiceComponent as traits, which represent different components of the
 * application.
 *
 * Implementation Traits: Provided concrete implementations for these
 * components in UserRepositoryComponentImpl and UserServiceComponentImpl.
 *
 * Dependency Injection: The UserServiceComponentImpl trait depends on the
 * UserRepositoryComponent trait, and this dependency is injected
 * using the this: UserRepositoryComponent self-type annotation.
 *
 * Mixing Components: In the DITypeSystem object, mix the
 * UserRepositoryComponentImpl and UserServiceComponentImpl traits to create
 * a complete application with all the necessary components.
 *
 */

trait UserRepositoryComponent:
  def userRepository: UserRepository

trait UserRepositoryComponentImpl extends UserRepositoryComponent:
  val userRepository: UserRepository = new UserRepositoryImpl

trait UserRepository:
  def getUser(id: Int): String

class UserRepositoryImpl extends UserRepository:
  def getUser(id: Int): String = s"User $id"

trait UserServiceComponent:
  def userService: UserService

trait UserServiceComponentImpl extends UserServiceComponent:
  this: UserRepositoryComponent =>
  val userService: UserService = new UserServiceImpl(userRepository)

trait UserService:
  def getUserName(id: Int): String

class UserServiceImpl(userRepository: UserRepository) extends UserService:
  def getUserName(id: Int): String = userRepository.getUser(id)

object DITypeSystem extends App:
  object Components extends UserRepositoryComponentImpl with UserServiceComponentImpl
  val userService = Components.userService
  println(userService.getUserName(1))