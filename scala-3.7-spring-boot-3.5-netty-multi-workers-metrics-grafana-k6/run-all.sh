#!/bin/bash

set -e
echo "Building all Scala applications..."
sbt clean
sbt "project fastEndpoints" assembly
sbt "project slowEndpoints" assembly
sbt "project healthChecker" assembly
echo "Starting all containers with podman-compose..."
podman-compose up -d
echo "Waiting for services to be healthy..."
MAX_WAIT=60
ELAPSED=0

while [ $ELAPSED -lt $MAX_WAIT ]; do
  if podman exec postgres-db pg_isready -U postgres > /dev/null 2>&1; then
    echo "PostgreSQL is ready!"
    break
  fi
  sleep 1
  ELAPSED=$((ELAPSED + 1))
done
if [ $ELAPSED -ge $MAX_WAIT ]; then
  echo "Timeout waiting for PostgreSQL"
  exit 1
fi

sleep 1
ELAPSED=0
while [ $ELAPSED -lt $MAX_WAIT ]; do
  FAST_OK=false
  SLOW_OK=false
  HC_OK=false
  if curl -s http://localhost:8081/actuator/health > /dev/null 2>&1; then
    FAST_OK=true
  fi
  if curl -s http://localhost:8082/actuator/health > /dev/null 2>&1; then
    SLOW_OK=true
  fi
  if curl -s http://localhost:8083/actuator/health > /dev/null 2>&1; then
    HC_OK=true
  fi
  if [ "$FAST_OK" = true ] && [ "$SLOW_OK" = true ] && [ "$HC_OK" = true ]; then
    echo "All applications are ready!"
    break
  fi
  sleep 1
  ELAPSED=$((ELAPSED + 1))
done

if [ $ELAPSED -ge $MAX_WAIT ]; then
  echo "Timeout waiting for Applications"
  exit 1
fi
echo "All services are up and running!"
echo "Fast Endpoints: http://localhost:8081"
echo "Slow Endpoints: http://localhost:8082"
echo "Health Checker: http://localhost:8083"
echo "Grafana: http://localhost:3000 (admin/admin)"
echo "Prometheus: http://localhost:9090"
