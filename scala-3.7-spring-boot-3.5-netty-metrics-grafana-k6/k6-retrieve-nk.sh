#!/bin/bash
read -p "Enter number of virtual users: " USERS
if ! [[ "$USERS" =~ ^[0-9]+$ ]]; then
  echo "Invalid number"
  exit 1
fi
echo "Running K6 stress test with $USERS virtual users..."
VUS=$USERS DURATION=30s k6 run --out experimental-prometheus-rw k6-test-retrieve.js
