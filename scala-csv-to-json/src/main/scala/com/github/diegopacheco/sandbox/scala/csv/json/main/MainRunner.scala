package com.github.diegopacheco.sandbox.scala.csv.json.main

import com.github.tototoshi.csv._
import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.OptionModule
import com.fasterxml.jackson.module.scala.TupleModule
import java.io.StringWriter
import java.io.PrintWriter

object MainRunner extends App {
    
    val module = new OptionModule with TupleModule {}
    val mapper = new ObjectMapper()
    mapper.registerModule(module)
    mapper.registerModule(DefaultScalaModule)  
    println("Mapper Ref: " + mapper)
    
    val reader = CSVReader.open(new File(new File(".").getCanonicalPath() + "/src/main/resources/file.csv"))
    println("CSV Ref: " + reader)
    
    var collection:List[Pojo] = List[Pojo]()
    
    val it = reader.iterator
    while(it.hasNext){
       val v:List[String] = it.next.toList
       
       val pojo:Pojo = new Pojo
       try{
         pojo.End_Latitude             = v(0)
         pojo.End_Longitude            = v(1).split(";")(0)
         pojo.Cad_CNAE                 = v(1).split(";")(1)
         pojo.Cad_Porte                = v(2).split(";")(0)
         pojo.Cad_FaixaFuncionario     = v(2).split(";")(1)
         pojo.Cad_FaturamentoPresumido = v(2).split(";")(2)
         pojo.Cad_RiscoPrescreen       = v(2).split(";")(3)  
       }catch{
         case _:Exception =>
       }
       
       collection = collection :+ pojo
    }
    
    val out = new StringWriter
    mapper.writeValue(out, collection)
    val json = out.toString()
    println(json)
    
    val writer:PrintWriter = new PrintWriter("d:/data.json", "UTF-8")
    writer.println(json);
    writer.close();
    
    
}