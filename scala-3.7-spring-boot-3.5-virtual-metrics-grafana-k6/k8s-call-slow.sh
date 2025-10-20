#!/bin/bash
set -e
PORT=8081
LOCAL_PORT=8082
echo "Starting port-forward for scala-app..."
kubectl port-forward svc/scala-app ${LOCAL_PORT}:${PORT} > /dev/null 2>&1 &
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
echo "Calling slow endpoint with 60 seconds delay..."
curl -i http://localhost:${LOCAL_PORT}/slow/60
echo ""
echo "Stopping port-forward..."
kill $PF_PID 2>/dev/null || true
echo "Done!"
