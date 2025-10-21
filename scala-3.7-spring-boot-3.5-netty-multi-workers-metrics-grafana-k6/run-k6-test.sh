#!/bin/bash

echo "Running k6 load test with Prometheus Remote Write..."

K6_PROMETHEUS_RW_SERVER_URL=http://localhost:9090/api/v1/write \
K6_PROMETHEUS_RW_TREND_AS_NATIVE_HISTOGRAM=true \
k6 run -o experimental-prometheus-rw k6-load-test.js
