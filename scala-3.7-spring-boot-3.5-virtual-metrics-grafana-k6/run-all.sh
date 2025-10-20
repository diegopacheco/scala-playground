#!/bin/bash

set -e
echo "Building Scala application..."
sbt clean assembly
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
  if curl -s http://localhost:8182/actuator/health > /dev/null 2>&1; then
    echo "Application is ready!"
    break
  fi
  sleep 1
  ELAPSED=$((ELAPSED + 1))
done

if [ $ELAPSED -ge $MAX_WAIT ]; then
  echo "Timeout waiting for Application"
  exit 1
fi
echo "All services are up and running!"
echo "Application: http://localhost:8081"
echo "Grafana: http://localhost:3000 (admin/admin)"
echo "Prometheus: http://localhost:9090"
