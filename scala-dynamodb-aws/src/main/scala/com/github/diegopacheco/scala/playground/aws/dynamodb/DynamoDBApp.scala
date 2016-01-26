package com.github.diegopacheco.scala.playground.aws.dynamodb

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition
import com.amazonaws.services.dynamodbv2.document.Item
import java.util.Arrays
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.model.KeyType
import java.io.File
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import com.amazonaws.services.dynamodbv2.document.utils.NameMap
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import com.amazonaws.services.dynamodbv2.document.ItemCollection
import com.amazonaws.services.dynamodbv2.document.ScanOutcome
import com.amazonaws.regions.Regions

object DynamoDBApp extends App {

  //val client: AmazonDynamoDBClient = new AmazonDynamoDBClient().withEndpoint("http://localhost:8000")
  val client:AmazonDynamoDBClient = new AmazonDynamoDBClient().withRegion(Regions.US_WEST_1)
  val dynamoDB: DynamoDB = new DynamoDB(client)
  
  def createTable(){
     try {
          val tableName = "Movies"
          println("Attempting to create table; please wait...")
          val table: Table = dynamoDB.createTable(tableName,
            Arrays.asList(
              new KeySchemaElement("year", KeyType.HASH), //Partition key
              new KeySchemaElement("title", KeyType.RANGE)), //Sort key
            Arrays.asList(
              new AttributeDefinition("year", ScalarAttributeType.N),
              new AttributeDefinition("title", ScalarAttributeType.S)),
            new ProvisionedThroughput(10L, 10L));
          table.waitForActive();
          System.out.println("Success.  Table status: " + table.getDescription().getTableStatus())
      } catch {
        case (e: Exception) =>
          System.err.println("Unable to create table: ")
      } 
  }
  
  def insertData(){
       val table = dynamoDB.getTable("Movies")
       val parser:JsonParser = new JsonFactory().createParser(new File( new File(".").getCanonicalPath + "/src/main/resources/moviedata.json"))
       val rootNode:JsonNode = new ObjectMapper().readTree(parser)
       val iter = rootNode.iterator()
    
            while (iter.hasNext()) {
                var currentNode = iter.next()
    
                val year:Int = currentNode.path("year").asInt()
                val title:String  = currentNode.path("title").asText()
    
                try {
                    table.putItem(new Item()
                    .withPrimaryKey("year", year, "title", title)
                    .withJSON("info", currentNode.path("info").toString()))
                    println("PutItem succeeded: " + year + " " + title)
    
                } catch  {
                    case (e:Exception) =>
                      System.err.println("Unable to add movie: " + year + " " + title)
                      System.err.println(e.getMessage());
                }
            }
            parser.close()
  }
  
  def addOneIten(){
     val table:Table = dynamoDB.getTable("Movies")
     val year:Int  = 2015
     val title:String = "The Big New Movie"
     
     try {
          println("Adding a new item...")
          val outcome:PutItemOutcome = table.putItem(new Item()
              .withPrimaryKey("year", year, "title", title)
              .withJSON("info", "{\"plot\" : \"Something happens.\"}"))
          
          println("PutItem succeeded:\n" + outcome.getPutItemResult())

      } catch {
         case (e:Exception) =>
          System.err.println("Unable to add item: " + year + " " + title)
      }
  }
  
  def ScanData(){
     val table:Table = dynamoDB.getTable("Movies")
     val scanSpec:ScanSpec = new ScanSpec()
            .withProjectionExpression("#yr, title, info.rating")
            .withFilterExpression("#yr between :start_yr and :end_yr")
            .withNameMap(new NameMap().`with`("#yr",  "year"))
            .withValueMap(new ValueMap().withNumber(":start_yr", 1950).withNumber(":end_yr", 1959))

        try {
            val items:ItemCollection[ScanOutcome] = table.scan(scanSpec)
            val iter = items.iterator()
            while (iter.hasNext()) {
                val item:Item = iter.next()
                println(item.toString())
            }
            
        } catch {
            case (e:Exception) => 
            System.err.println("Unable to scan the table:");
        }
  }
  
  createTable()
  insertData()
  addOneIten()
  ScanData()
   
}