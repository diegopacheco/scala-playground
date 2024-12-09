#!/bin/bash

sbt assembly
java -jar target/scala-3.6.1/snake-game-scala-3.6-assembly-1.0.jar