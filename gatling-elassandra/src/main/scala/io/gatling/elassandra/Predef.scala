package io.gatling.elassandra

object Predef {
  
  def elassandra(
      clusterName:String  = "Localhost",
      clusterContactPoint:String ="172.28.198.16",
      keyspaceName:String = "customer",
      tableName:String = "external"
  ):ElassandraProtocol = 
    new ElassandraProtocol(clusterName,clusterContactPoint,keyspaceName,tableName)
  
}