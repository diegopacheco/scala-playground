#!/bin/bash
set -e
PORT=3000
LOCAL_PORT=3000
URL="http://localhost:${LOCAL_PORT}"
echo "Starting port-forward for Grafana..."
kubectl port-forward svc/grafana ${LOCAL_PORT}:${PORT} > /dev/null 2>&1 &
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
echo "Grafana URL: ${URL}"
echo "Default credentials: admin / admin"
echo ""
echo "Opening browser..."
open ${URL}
echo ""
echo "Port-forward is running in the background (PID: ${PF_PID})"
echo "Press Ctrl+C to stop port-forward and exit"
echo ""
trap "echo 'Stopping port-forward...'; kill $PF_PID 2>/dev/null || true; exit 0" INT TERM
wait $PF_PID
