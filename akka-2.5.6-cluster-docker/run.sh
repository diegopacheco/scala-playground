#!/bin/bash

#
# Terminal 1
#
sbt clean compile docker:publishLocal
docker-compose up

#
# Other terminal - 2
#
# docker-compose scale node=5
#
