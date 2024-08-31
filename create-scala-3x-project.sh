#!/bin/sh

touch README.md
echo "### Build
\`\`\`
sbt compile
\`\`\`

### Run
\`\`\`
sbt run
\`\`\`
" > README.md

mkdir -p src/test/scala
mkdir -p src/test/resources
mkdir -p src/test/java
mkdir -p src/main/java 
mkdir -p src/main/resources 
mkdir -p src/main/scala
mkdir project target
touch project/build.properties
touch project/plugins.sbt 

projectName=${PWD##*/}

echo "name := \"$projectName\"
version := \"1.0\"
scalaVersion := \"3.5.0\"" > build.sbt

echo "sbt.version=1.10.1" >  project/build.properties 

sbt compile
rm -rf .bsp/
rm -rf .idea/
