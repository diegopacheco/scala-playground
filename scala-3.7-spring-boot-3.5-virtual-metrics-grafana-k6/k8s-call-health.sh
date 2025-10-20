#!/bin/bash
set -e
MGMT_PORT=8182
LOCAL_PORT=8183
POD_NAME=$(kubectl get pods -l app=scala-app -o jsonpath='{.items[0].metadata.name}')
echo "Starting port-forward for scala-app management port (pod: ${POD_NAME})..."
kubectl port-forward pod/${POD_NAME} ${LOCAL_PORT}:${MGMT_PORT} > /dev/null 2>&1 &
PF_PID=$!
echo "Port-forward PID: ${PF_PID}"
echo "Waiting for port-forward to be ready..."
MAX_WAIT=30
WAITED=0
while ! nc -z localhost ${LOCAL_PORT} 2>/dev/null; do
  if [ $WAITED -ge $MAX_WAIT ]; then
    echo "Port-forward failed to start after ${MAX_WAIT} seconds"
    kill $PF_PID 2>/dev/null || true
    exit 1
  fi
  sleep 1
  WAITED=$((WAITED + 1))
done
echo "Port-forward ready!"
echo ""
echo "Calling health endpoint..."
curl -s http://localhost:${LOCAL_PORT}/actuator/health | jq '.'
echo ""
echo "Calling readiness endpoint..."
curl -s http://localhost:${LOCAL_PORT}/actuator/health/readiness | jq '.'
echo ""
echo "Calling liveness endpoint..."
curl -s http://localhost:${LOCAL_PORT}/actuator/health/liveness | jq '.'
echo ""
echo "Stopping port-forward..."
kill $PF_PID 2>/dev/null || true
echo "Done!"
