package com.github.diegopacheco.scala3x.playground

//
// Opaque types provide a way to define new types that are distinct from their
// underlying types but have no runtime overhead.
//
object OpaqueTypes extends App:
  opaque type UserId = String

  object UserId:
    def apply(id: String): UserId = id
    def getUser(id: UserId): String = s"User with ID: $id"

  import UserId.getUser

  val userId:UserId = UserId("12345")
  val user = getUser(userId) // "User with ID: 12345"
  println(s"user: ${user} - userId: ${userId}")