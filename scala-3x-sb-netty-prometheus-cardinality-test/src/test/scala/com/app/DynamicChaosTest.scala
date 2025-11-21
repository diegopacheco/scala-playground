package com.app

import org.junit.jupiter.api.{DynamicTest, TestFactory, BeforeAll}
import org.junit.jupiter.api.Assertions.*
import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import scala.jdk.CollectionConverters.*
import scala.util.matching.Regex

class DynamicChaosTest():

  private val baseUrl = "http://localhost:8081"
  private val prometheusUrl = s"$baseUrl/prometheus"
  private val maxCardinality = 99

  @TestFactory
  def testChaosCardinality(): java.util.Collection[DynamicTest] =
    generateChaosLoad(1000)

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

  private def generateChaosLoad(requests: Int): Unit =
    println(s"Starting chaos test - generating $requests unique requests per endpoint...")
    val client = HttpClient.newHttpClient()

    for (i <- 1 to requests) {
      makeRequest(client, s"$baseUrl/user/$i")
      makeRequest(client, s"$baseUrl/product/$i")
      makeRequest(client, s"$baseUrl/order/$i")

      if (i % 100 == 0) {
        println(s"Completed $i requests per endpoint...")
      }
    }

    println(s"Chaos test completed! Generated ${requests * 3} total unique requests ($requests per endpoint)")

  private def makeRequest(client: HttpClient, url: String): Unit =
    try {
      val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build()
      client.send(request, HttpResponse.BodyHandlers.ofString())
    } catch {
      case e: Exception => println(s"Request failed for $url: ${e.getMessage}")
    }

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
