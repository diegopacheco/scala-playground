#!/bin/bash
set -e
PROM_PORT=9090
PROM_LOCAL_PORT=9091
APP_PORT=8081
APP_LOCAL_PORT=8082
echo "Starting port-forward for Prometheus..."
kubectl port-forward svc/prometheus ${PROM_LOCAL_PORT}:${PROM_PORT} > /dev/null 2>&1 &
PROM_PF_PID=$!
echo "Starting port-forward for scala-app..."
kubectl port-forward svc/scala-app ${APP_LOCAL_PORT}:${APP_PORT} > /dev/null 2>&1 &
APP_PF_PID=$!
echo "Port-forward PIDs: Prometheus=${PROM_PF_PID}, App=${APP_PF_PID}"
echo "Waiting for port-forwards to be ready..."
MAX_WAIT=30
WAITED=0
while ! nc -z localhost ${PROM_LOCAL_PORT} 2>/dev/null || ! nc -z localhost ${APP_LOCAL_PORT} 2>/dev/null; do
  if [ $WAITED -ge $MAX_WAIT ]; then
    echo "Port-forward failed to start after ${MAX_WAIT} seconds"
    kill $PROM_PF_PID $APP_PF_PID 2>/dev/null || true
    exit 1
  fi
  sleep 1
  WAITED=$((WAITED + 1))
done
echo "Port-forwards ready!"
echo ""
echo "Creating test data..."
curl -s http://localhost:${APP_LOCAL_PORT}/create/5 > /dev/null
echo ""
echo "Running K6 load test (10 VUs for 30s)..."
echo "K6 metrics will appear in Grafana dashboard during the test"
echo ""
cat > /tmp/k6-test.js <<'EOF'
import http from 'k6/http';
import { check, sleep } from 'k6';
export let options = {
  vus: 10,
  duration: '30s',
};
export default function () {
  let response = http.get('http://localhost:8082/retrieve');
  check(response, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
EOF
K6_PROMETHEUS_RW_SERVER_URL=http://localhost:${PROM_LOCAL_PORT}/api/v1/write k6 run --out experimental-prometheus-rw /tmp/k6-test.js
echo ""
echo "Test complete! K6 metrics should now be visible in Grafana"
echo "Open: http://localhost:30300/d/spring-tomcat-vt-k6"
echo ""
echo "Cleaning up..."
rm -f /tmp/k6-test.js
kill $PROM_PF_PID $APP_PF_PID 2>/dev/null || true
echo "Done!"
