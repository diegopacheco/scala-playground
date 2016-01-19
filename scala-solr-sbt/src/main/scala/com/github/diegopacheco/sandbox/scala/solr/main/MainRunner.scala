package com.github.diegopacheco.sandbox.scala.solr.main

object MainRunner extends App {
    
    import jp.sf.amateras.solr.scala._
    
    val client = new SolrClient("http://127.0.0.1:8983/solr/maps2/")
    println("Client: " + client)
    
    val result = client.query("*:*").getResultAsMap()
    println("Result: " + result)
  
}