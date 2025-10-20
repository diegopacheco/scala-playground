#!/bin/bash
echo "=== Checking Prometheus Targets ==="
curl -s http://localhost:9090/api/v1/targets | jq '.data.activeTargets[] | {job: .labels.job, health: .health, lastError: .lastError}'
echo ""
echo "=== Checking if Tomcat metrics exist in Prometheus ==="
curl -s 'http://localhost:9090/api/v1/label/__name__/values' | jq -r '.data[]' | grep -i tomcat | sort
echo ""
echo "=== Checking specific thread metrics ==="
curl -s 'http://localhost:9090/api/v1/label/__name__/values' | jq -r '.data[]' | grep -i "thread" | sort
echo ""
echo "=== Query Tomcat threads from Prometheus ==="
curl -s 'http://localhost:9090/api/v1/query?query=tomcat_threads_busy_threads' | jq '.'
