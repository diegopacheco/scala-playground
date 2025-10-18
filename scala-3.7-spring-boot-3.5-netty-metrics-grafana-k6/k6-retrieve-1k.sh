#!/bin/bash
echo "Running K6 stress test with 1000 virtual users..."
VUS=1000 DURATION=30s k6 run --out experimental-prometheus-rw k6-test-retrieve.js
