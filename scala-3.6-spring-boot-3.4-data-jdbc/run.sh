#!/bin/bash

sbt clean compile assembly

java -jar target/scala-3.6.2/scala3-springboot-3.jar