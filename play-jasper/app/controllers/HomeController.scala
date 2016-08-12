package controllers

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.sql.Connection
import java.sql.DriverManager
import java.util.HashMap

import javax.inject.Inject
import javax.inject.Singleton
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import play.api.libs.concurrent.Execution.Implicits
import play.api.libs.iteratee.Enumerator
import play.api.mvc.Action
import play.api.mvc.Controller

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    
    try {
      val os:OutputStream = new ByteArrayOutputStream()
      val reportParams:java.util.Map[String,Object] = new HashMap()
      val compiledFile = "/tmp/products.jasper"
        
      val con:Connection = DriverManager.getConnection("jdbc:mysql://localhost/RWS_DB?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
      
      JasperCompileManager.compileReportToFile("/home/diego/home2/diego/github/diegopacheco/scala-playground/play-jasper/src/main/resources/products.jrxml" , compiledFile)
      val jrprint:JasperPrint  = JasperFillManager.fillReport(compiledFile, reportParams, con)
      
      val exporter:JRPdfExporter  = new JRPdfExporter()
      exporter.setExporterInput(new SimpleExporterInput(jrprint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));      
      exporter.exportReport()
      
      Ok.chunked( Enumerator.fromStream(new ByteArrayInputStream((os.asInstanceOf[ByteArrayOutputStream]).toByteArray())) )
       
    } catch {
    case e:Exception => e.printStackTrace()
       null
    }
  }

}
