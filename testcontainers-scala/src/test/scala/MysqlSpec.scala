import org.scalatest.flatspec.AnyFlatSpec
import com.dimafeng.testcontainers.{ForAllTestContainer, MySQLContainer}

import java.sql.DriverManager

class MysqlSpec extends AnyFlatSpec with ForAllTestContainer {

  override val container: MySQLContainer = MySQLContainer()

  it should "do something" in {
    print("Testing MYSQL connection... ")
    Class.forName(container.driverClassName)
    val connection = DriverManager.getConnection(container.jdbcUrl, container.username, container.password)
    print(connection)
  }
}