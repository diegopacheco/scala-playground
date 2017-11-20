#!/bin/bash

sbt clean compile docker:publishLocal
docker-compose up
