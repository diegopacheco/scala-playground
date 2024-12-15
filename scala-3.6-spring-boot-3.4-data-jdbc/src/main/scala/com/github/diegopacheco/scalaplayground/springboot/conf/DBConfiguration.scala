package com.github.diegopacheco.scalaplayground.springboot.conf

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableScheduling
class DBConfiguration {
  @Bean(name = Array("dataSource"))
  def getDataSource: DataSource = {
    val config = new HikariConfig
    config.setJdbcUrl("jdbc:mysql://127.0.0.1:3325/person")
    config.setUsername("root")
    config.setPassword("pass")
    config.addDataSourceProperty("cachePrepStmts", "true")
    config.addDataSourceProperty("prepStmtCacheSize", "250")
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
    val ds = new HikariDataSource(config)
    ds
  }

  @Bean(name = Array("transactionManager"))
  def txManager: PlatformTransactionManager = {
    System.out.println("New TXManager requested...")
    new DataSourceTransactionManager(getDataSource)
  }
}