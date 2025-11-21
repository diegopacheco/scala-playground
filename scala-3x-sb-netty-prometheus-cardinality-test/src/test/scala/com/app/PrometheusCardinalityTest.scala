package com.app

import org.junit.jupiter.api.{DynamicTest, TestFactory, Test}
import org.junit.jupiter.api.Assertions.*
import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import scala.jdk.CollectionConverters.*
import scala.util.matching.Regex

class PrometheusCardinalityTest():

  private val prometheusUrl = "http://localhost:8081/prometheus"
  private val maxCardinality = 99

  @TestFactory
  def testAllMetricsCardinality(): java.util.Collection[DynamicTest] =
    val metricsData = fetchPrometheusMetrics()
    val metricCardinalities = parseMetricsCardinality(metricsData)

    metricCardinalities.map { case (metricName, cardinality) =>
      DynamicTest.dynamicTest(s"Metric '$metricName' cardinality should be below $maxCardinality (actual: $cardinality)", () => {
        assertTrue(
          cardinality < maxCardinality,
          s"Metric '$metricName' has cardinality of $cardinality which exceeds the maximum of $maxCardinality"
        )
      })
    }.toSeq.asJava

  private def fetchPrometheusMetrics(): String =
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
      .uri(URI.create(prometheusUrl))
      .GET()
      .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    assertEquals(200, response.statusCode(), s"Failed to fetch metrics from $prometheusUrl")
    response.body()

  private def parseMetricsCardinality(metricsData: String): Map[String, Int] =
    val lines = metricsData.split("\n").filter(line =>
      line.trim.nonEmpty && !line.startsWith("#")
    )

    val metricPattern: Regex = """^([a-zA-Z_:][a-zA-Z0-9_:]*)(\{.*\})?(\s+.*)$""".r

    val metrics = lines.flatMap { line =>
      metricPattern.findFirstMatchIn(line).map { m =>
        val metricName = m.group(1)
        val labels = Option(m.group(2)).getOrElse("")
        (metricName, labels)
      }
    }

    metrics
      .groupBy(_._1)
      .view
      .mapValues(_.map(_._2).distinct.length)
      .toMap
