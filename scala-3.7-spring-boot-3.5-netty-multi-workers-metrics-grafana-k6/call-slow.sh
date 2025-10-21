#!/bin/bash

echo "Slow call - will take 1 min"
curl -i http://localhost:8082/slow/60
