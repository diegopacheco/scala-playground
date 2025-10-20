#!/bin/bash
echo "Checking available Tomcat metrics..."
echo ""
echo "=== All Tomcat Metrics ==="
curl -s http://localhost:8182/actuator/metrics | jq -r '.names[]' | grep -i tomcat | sort
echo ""
echo "=== Tomcat Thread Metrics Details ==="
for metric in $(curl -s http://localhost:8182/actuator/metrics | jq -r '.names[]' | grep -i "tomcat.*thread"); do
  echo ""
  echo "Metric: $metric"
  curl -s http://localhost:8182/actuator/metrics/$metric | jq '.'
done
