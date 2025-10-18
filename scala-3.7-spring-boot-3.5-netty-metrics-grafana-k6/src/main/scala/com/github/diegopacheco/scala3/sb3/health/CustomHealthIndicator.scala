package com.github.diegopacheco.scala3.sb3.health

import org.springframework.boot.actuate.health.{Health, HealthIndicator}
import org.springframework.stereotype.Component

import java.util.concurrent.atomic.AtomicBoolean

@Component
class CustomHealthIndicator extends HealthIndicator {

  private val healthy = new AtomicBoolean(true)

  override def health(): Health = {
    if (healthy.get()) {
      Health.up()
        .withDetail("status", "healthy")
        .withDetail("service", "running")
        .build()
    } else {
      Health.down()
        .withDetail("status", "unhealthy")
        .withDetail("service", "degraded")
        .build()
    }
  }

  def setHealthy(isHealthy: Boolean): Unit = {
    healthy.set(isHealthy)
  }

  def isHealthy: Boolean = healthy.get()
}
