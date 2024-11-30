#!/bin/bash

curl -i http://localhost:8080/buy/1 -X POST

curl http://localhost:8080/products | jq .