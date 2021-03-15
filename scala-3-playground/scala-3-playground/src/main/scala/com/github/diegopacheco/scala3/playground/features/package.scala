package com.github.diegopacheco.scala3.playground

package users {
  package administrators {
    class AdminUser
    val default = AdminUser()
  }
  package normalusers {
    class NormalUser
    val default = NormalUser()
  }
}

@main def PackageObjectMain():Unit = {

  import users.administrators._
  println(default)

  // HIDING MULTIPLE MEMBERS
  import java.util.{List=>_, Map=>_, Set=>_, _}
  
  
}