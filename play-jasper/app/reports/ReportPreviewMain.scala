package reports

import java.io.FileInputStream
import java.sql.Connection
import java.sql.DriverManager
import java.util.HashMap
import com.lowagie.text.pdf.codec.Base64.OutputStream
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.JasperReport
import net.sf.jasperreports.engine.util.JRLoader
import net.sf.jasperreports.engine.JasperCompileManager

object ReportPreviewMain extends App {
 
    try {
      val os:OutputStream = null
      val reportParams:java.util.Map[String,Object] = new HashMap()
      val compiledFile = "/tmp/products.jasper"
      val jrxmlFile = "/home/diego/home2/diego/github/diegopacheco/scala-playground/play-jasper/app/reports/products.jrxml"
      
      System.setProperty("java.awt.headless", "true")
      
      val con:Connection = DriverManager.getConnection("jdbc:mysql://localhost/RWS_DB?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
      
      JasperCompileManager.compileReportToFile(jrxmlFile, compiledFile)
      
      val jasperReport:JasperReport = JRLoader.loadObject( new FileInputStream(compiledFile) ).asInstanceOf[JasperReport]  
      
      val jrprint:JasperPrint  = JasperFillManager.fillReport(jasperReport, reportParams, con)
      
      JasperExportManager.exportReportToPdfFile(jrprint,"/tmp/products.pdf")
      
//      val jasperViewer:JasperViewer = new JasperViewer(jrprint)
//      jasperViewer.setVisible(true)
      
//      val exporter:JRPdfExporter  = new JRPdfExporter()
//      exporter.setExporterInput(new SimpleExporterInput(jrprint));
//      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));      
//      exporter.exportReport()

//      Ok.chunked( Enumerator.fromStream(new ByteArrayInputStream((os.asInstanceOf[ByteArrayOutputStream]).toByteArray())) )
       
    } catch {
    case e:Exception => e.printStackTrace()
       null
    }

  
}