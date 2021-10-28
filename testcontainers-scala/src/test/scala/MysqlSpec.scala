import org.scalatest.flatspec.AnyFlatSpec
import com.dimafeng.testcontainers.{ForAllTestContainer, MySQLContainer}

import java.sql.DriverManager

class MysqlSpec extends AnyFlatSpec with ForAllTestContainer {

  override val container: MySQLContainer = MySQLContainer()

  it should "do connect to mysql" in {
    println("Checking MySQL Driver: " + Class.forName(container.driverClassName))
    val jdbcUrl = container.jdbcUrl + "?autoReconnect=true&useSSL=false"
    println(s"Testing MYSQL connection: [${jdbcUrl} ${container.username} @ ${container.password}] ")
    val connection = DriverManager.getConnection(jdbcUrl, container.username, container.password)
    println(s"MySQL Connection: [${connection}] Working.")
  }
}