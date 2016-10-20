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

class ElassandraProtocol(clusterName:String, 
    clusterContactPoint:String, 
    keyspaceName:String, 
    tableName:String
) {  
  
  val writeConsistencyLevel = ConsistencyLevel.LOCAL_ONE
  val readConsistencyLevel =  ConsistencyLevel.LOCAL_ONE
  
  var cluster:Cluster = null
  var session:Session = null
  var readPstmt:PreparedStatement = null
  var writePstmt:PreparedStatement = null
  var readAllPstmt:PreparedStatement = null
  
  init()
  
  def read(key:String):String = {
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
  
  def readAll():String = {
      val bStmt:BoundStatement = readAllPstmt.bind()
      bStmt.setConsistencyLevel(this.readConsistencyLevel)
      val rs:ResultSet = session.execute(bStmt)
  
      val result:java.util.List[Row] = rs.all()
      if (!result.isEmpty()){
          return result.size() + ""
      }
      return null 
	}
  
  def write():String = {
        val key:String = UUID.randomUUID().toString()
	      val bStmt:BoundStatement = writePstmt.bind()
        bStmt.setString("\"_id\"", key)
        bStmt.setList("name", Arrays.asList(UUID.randomUUID().toString()))
        bStmt.setConsistencyLevel(this.writeConsistencyLevel)

        session.execute(bStmt)
        return key;  			
	}
  
  def shutdown():Unit = {
      this.cluster.close()
  }
  
  def close():Unit = {
      this.session.close()
  }
  
  def init():Unit = {
        val cluster = Cluster.builder()
                .withProtocolVersion(ProtocolVersion.V2)
                .withClusterName(clusterName)
                .addContactPoint(clusterContactPoint)
                .build()
        val session = cluster.connect()
        
        this.cluster = cluster
        this.session = session
        
        initKeyspace(session)
        initTables(session)

        this.writePstmt = session.prepare("INSERT INTO "+ tableName +" (\"_id\", name) VALUES (?, ?)")
        this.readPstmt = session.prepare("SELECT * From "+ tableName +" Where \"_id\" = ?")
        this.readAllPstmt = session.prepare("SELECT * From " + tableName)
        close()
  }
  
   def open():Unit = {
        val session = cluster.connect()
        this.session = session
        
        session.execute("Use " + keyspaceName);
  }
  
  def initKeyspace(session:Session):Unit = {
     session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName +" WITH replication = {'class': 'NetworkTopologyStrategy', 'dc1': '1'}  AND durable_writes = true;");
     session.execute("Use " + keyspaceName);
  }
    
  def initTables(session:Session):Unit = {
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
  
}

object ElassandraProtocolMainTest extends App {
    
    val ep:ElassandraProtocol = new ElassandraProtocol("Localhost","172.28.198.16", "customer", "external" )
    
    ep.open()
    val r = ep.write()
    val r2 = ep.read(r)
    println(r2)
    ep.close()
    
    ep.open()
    println("Read All " + ep.readAll())
    ep.close()
    
    ep.open()
    println("Read All " + ep.readAll())
    ep.close()
    
    ep.open()
    println("Read All " + ep.readAll())
    ep.close()
    
    ep.shutdown()
}
