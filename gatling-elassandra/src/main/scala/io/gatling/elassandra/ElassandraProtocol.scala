package io.gatling.elassandra;

import com.datastax.driver.core.BoundStatement
import com.datastax.driver.core.Cluster
import com.datastax.driver.core.ConsistencyLevel
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.core.ResultSet
import com.datastax.driver.core.Row
import com.datastax.driver.core.Session
import java.util.UUID
import java.util.Arrays
import com.datastax.driver.core.ProtocolVersion
import java.util.function.Consumer
import com.datastax.driver.core.SocketOptions

object ElassandraCassClusterManager {
    
    implicit def toConsumer[A](function: A => Unit): Consumer[A] = new Consumer[A]() {
      override def accept(arg: A): Unit = function.apply(arg)
    }
  
    var cluster:Cluster = null
    
    var readPstmt:PreparedStatement = null
    var writePstmt:PreparedStatement = null
    var readAllPstmt:PreparedStatement = null
  
    def init(keyspaceName:String,clusterName:String,clusterContactPoint:String,tableName:String):Unit = {
      synchronized{
          if (cluster==null){
             
             cluster = Cluster.builder()
                    .withProtocolVersion(ProtocolVersion.V3)
                    .withSocketOptions(new SocketOptions().setConnectTimeoutMillis(20000))                
                    .withClusterName(clusterName)
                    .addContactPoint(clusterContactPoint)
                    .build()
             this.cluster = cluster
             
             val session = cluster.connect()
            
             initKeyspace(session,keyspaceName)
             initTables(session,tableName)
    
             this.writePstmt   = session.prepare("INSERT INTO "+ tableName +" (\"_id\", name) VALUES (?, ?)")
             this.readPstmt    = session.prepare("SELECT * From "+ tableName +" Where \"_id\" = ?")
             this.readAllPstmt = session.prepare("SELECT * From " + tableName)
             
             Unit
          }
    
      }
    }
    
   def initKeyspace(session:Session,keyspaceName:String):Unit = {
     session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName +" WITH replication = {'class': 'NetworkTopologyStrategy', 'dc1': '1'}  AND durable_writes = true;");
     session.execute("Use " + keyspaceName);
   }
    
  def initTables(session:Session,tableName:String):Unit = {
        session.execute("CREATE TABLE IF NOT EXISTS "+ tableName +" (\"_id\" text PRIMARY KEY, name list<text>) WITH bloom_filter_fp_chance = 0.01 " + 
        		       " AND caching = '{\"keys\":\"ALL\", \"rows_per_partition\":\"NONE\"}'" + 
        		       " AND comment = 'Auto-created by Elassandra' " + 
        		       " AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'} " + 
        		       " AND compression = {'sstable_compression': 'org.apache.cassandra.io.compress.LZ4Compressor'} " + 
        		       " AND dclocal_read_repair_chance = 0.1  " + 
        		       " AND default_time_to_live = 0 " + 
        		       " AND gc_grace_seconds = 864000 " +
        		       " AND max_index_interval = 2048 " + 
        		       " AND memtable_flush_period_in_ms = 0 " + 
        		       " AND min_index_interval = 128 " + 
        		       " AND read_repair_chance = 0.0 " + 
        		       " AND speculative_retry = '99.0PERCENTILE'; ");
        
        session.execute("CREATE CUSTOM INDEX IF NOT EXISTS elastic_external_name_idx ON customer.external (name) "   
                        + " USING 'org.elasticsearch.cassandra.index.ExtendedElasticSecondaryIndex';");
    }
  
    def shutdown():Unit = {
      try{
        cluster.close()
      }
      catch{
        case e:com.datastax.driver.core.exceptions.NoHostAvailableException => 
            e.getErrors.values().forEach( (t:Throwable) => println(t) )  
            throw e
        case e:Throwable => throw e    
      }
    }
  
}

class ElassandraProtocol(clusterName:String, 
    clusterContactPoint:String, 
    keyspaceName:String, 
    tableName:String
) {  
  
  import ElassandraCassClusterManager._
  
  val writeConsistencyLevel = ConsistencyLevel.LOCAL_ONE
  val readConsistencyLevel =  ConsistencyLevel.LOCAL_ONE
  
  init(keyspaceName,clusterName,clusterContactPoint,tableName)
  
  
  def read(session:Session,key:String):String = {
      val bStmt:BoundStatement = readPstmt.bind()
      bStmt.setString("\"_id\"", key)
      bStmt.setConsistencyLevel(this.readConsistencyLevel)
      val rs:ResultSet = session.execute(bStmt)
  
      val result:java.util.List[Row] = rs.all()
      if (!result.isEmpty()){
          if (result.size() != 1) 
              throw new Exception("Num Cols returned not ok " + result.size())
      }
      else {
          return null
      }
      return "ok"
	}
  
  def readAll(session:Session):String = {
      try{
        val bStmt:BoundStatement = readAllPstmt.bind()
        bStmt.setConsistencyLevel(this.readConsistencyLevel)
        val rs:ResultSet = session.execute(bStmt)
    
        val result:java.util.List[Row] = rs.all()
        if (!result.isEmpty()){
            return result.size() + ""
        }
      }catch{
        case e:com.datastax.driver.core.exceptions.NoHostAvailableException => 
            e.getErrors.values().forEach( (t:Throwable) => println(t) )  
            throw e
        case e:Throwable => throw e    
      }
      return null        
	}
  
  def write(session:Session):String = {
        val key:String = UUID.randomUUID().toString()
	      val bStmt:BoundStatement = writePstmt.bind()
        bStmt.setString("\"_id\"", key)
        bStmt.setList("name", Arrays.asList(UUID.randomUUID().toString()))
        bStmt.setConsistencyLevel(this.writeConsistencyLevel)

        session.execute(bStmt)
        return key;  			
	}
  
  def close(session:Session):Unit = {
    try{
      session.close()
    } catch{
        case e:com.datastax.driver.core.exceptions.NoHostAvailableException => 
            e.getErrors.values().forEach( (t:Throwable) => println(t) )  
            throw e
        case e:Throwable => throw e    
      }
  }
  
  def open():Session = {
     val session = cluster.connect()
     session.execute("Use " + keyspaceName)
     session
  }
  
  def shutdownCluster():Unit = {
     shutdown()
  }
 
}

object ElassandraProtocolMainTest extends App {
    
    val ep:ElassandraProtocol = new ElassandraProtocol("Localhost","172.28.198.16", "customer", "external" )
    
    var session = ep.open()
    val r = ep.write(session)
    val r2 = ep.read(session,r)
    println(r2)
    ep.close(session)
    
    session = ep.open()
    println("Read All " + ep.readAll(session))
    ep.close(session)
    
    session = ep.open()
    println("Read All " + ep.readAll(session))
    ep.close(session)
    
    session = ep.open()
    println("Read All " + ep.readAll(session))
    ep.close(session)
    
    ep.shutdownCluster()
}
