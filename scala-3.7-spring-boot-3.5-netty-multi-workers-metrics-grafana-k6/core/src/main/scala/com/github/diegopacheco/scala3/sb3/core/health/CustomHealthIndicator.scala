package com.github.diegopacheco.scala3.sb3.core.health

import org.springframework.boot.actuate.health.{Health, HealthIndicator}
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean

@Component
class CustomHealthIndicator extends HealthIndicator {

  private val healthy = new AtomicBoolean(true)

  @Async("healthCheckExecutor")
  def checkHealthAsync(): CompletableFuture[Health] = {
    CompletableFuture.completedFuture(
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
    )
  }

  override def health(): Health = {
    checkHealthAsync().get()
  }

  def setHealthy(isHealthy: Boolean): Unit = {
    healthy.set(isHealthy)
  }

  def isHealthy: Boolean = healthy.get()
}
