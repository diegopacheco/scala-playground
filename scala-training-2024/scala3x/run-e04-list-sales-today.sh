#!/bin/bash

current_date=$(date +%F)
curl -s "http://localhost:8080/sales/$current_date" | jq .