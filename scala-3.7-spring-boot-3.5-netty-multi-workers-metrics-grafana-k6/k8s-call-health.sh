#!/bin/bash
set -e
echo "Calling health checker service..."
echo ""
echo "Calling health endpoint..."
curl -s http://localhost:30083/actuator/health | jq '.'
echo ""
echo "Calling readiness endpoint..."
curl -s http://localhost:30083/actuator/health/readiness | jq '.'
echo ""
echo "Calling liveness endpoint..."
curl -s http://localhost:30083/actuator/health/liveness | jq '.'
echo ""
echo "Done!"
