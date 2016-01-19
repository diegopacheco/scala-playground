package com.github.diegopacheco.sandbox.scala.couchdb.main

import com.ibm.couchdb.TypeMapping
import com.ibm.couchdb.CouchDb

object MainRunner extends App {
    
  case class Person(name: String, age: Int)
 
  val typeMapping = TypeMapping(classOf[Person] -> "Person")
  
  val alice = Person("Alice", 25)
  val bob   = Person("Bob", 30)
  val carl  = Person("Carl", 20)
  
  val couch  = CouchDb("127.0.0.1", 5984)
  val dbName = "couchdb-people1"
  
  val db     = couch.db(dbName, typeMapping)
  println("DB: " + db)
  
  couch.dbs.create(dbName).ignoreError
  db.docs.createMany(Seq(alice, bob, carl)).attemptRun
  
  println(db.docs.getMany.queryIncludeDocs[Person].attemptRun)

}