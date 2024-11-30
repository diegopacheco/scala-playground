#!/bin/bash

curl -X POST http://localhost:8080/pass/bike/NYC112
curl -X POST http://localhost:8080/pass/car/CAW123
curl -X POST http://localhost:8080/pass/truck/TXT125

curl -s http://localhost:8080/tolls | jq .