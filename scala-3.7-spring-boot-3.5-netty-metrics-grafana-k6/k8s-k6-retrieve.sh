#!/bin/bash
set -e
PORT=8081
LOCAL_PORT=8082
PROM_PORT=9090
PROM_LOCAL_PORT=9091
echo "Starting port-forward for scala-app..."
kubectl port-forward svc/scala-app ${LOCAL_PORT}:${PORT} > /dev/null 2>&1 &
PF_PID=$!
echo "Starting port-forward for Prometheus..."
kubectl port-forward svc/prometheus ${PROM_LOCAL_PORT}:${PROM_PORT} > /dev/null 2>&1 &
PROM_PF_PID=$!
echo "Port-forward PIDs: App=${PF_PID}, Prometheus=${PROM_PF_PID}"
echo "Waiting for port-forwards to be ready..."
MAX_WAIT=30
WAITED=0
while ! nc -z localhost ${LOCAL_PORT} 2>/dev/null || ! nc -z localhost ${PROM_LOCAL_PORT} 2>/dev/null; do
  if [ $WAITED -ge $MAX_WAIT ]; then
    echo "Port-forward failed to start after ${MAX_WAIT} seconds"
    kill $PF_PID $PROM_PF_PID 2>/dev/null || true
    exit 1
  fi
  sleep 1
  WAITED=$((WAITED + 1))
done
echo "Port-forwards ready!"
echo ""
echo "Creating initial data..."
curl -s http://localhost:${LOCAL_PORT}/create/10 > /dev/null
echo "Created 10 initial entries"
echo ""
echo "Running K6 stress test with 1000 virtual users for 60 seconds..."
echo ""
cat > /tmp/k6-k8s-retrieve.js <<'EOF'
import http from 'k6/http';
import { check, sleep } from 'k6';
export let options = {
  vus: 1000,
  duration: '60s',
};
export default function () {
  let response = http.get('http://localhost:8082/retrieve');
  check(response, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
EOF
K6_PROMETHEUS_RW_SERVER_URL=http://localhost:${PROM_LOCAL_PORT}/api/v1/write k6 run --out experimental-prometheus-rw /tmp/k6-k8s-retrieve.js
K6_EXIT_CODE=$?
echo ""
echo "Cleaning up..."
rm -f /tmp/k6-k8s-retrieve.js
kill $PF_PID $PROM_PF_PID 2>/dev/null || true
echo "Port-forwards stopped"
echo "Done!"
exit $K6_EXIT_CODE
