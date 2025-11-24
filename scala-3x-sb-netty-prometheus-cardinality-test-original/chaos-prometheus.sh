#!/bin/bash

BASE_URL="http://localhost:8081"

echo "Starting chaos test - generating 200 unique requests per endpoint..."

for i in {1..200}; do
  curl -s "$BASE_URL/user/$i" > /dev/null
  curl -s "$BASE_URL/product/$i" > /dev/null
  curl -s "$BASE_URL/order/$i" > /dev/null
  if [ $((i % 20)) -eq 0 ]; then
    echo "Completed $i requests per endpoint..."
  fi
done

echo "Chaos test completed! Generated 600 total unique requests (200 per endpoint)"
echo "Check metrics at: $BASE_URL/prometheus"
