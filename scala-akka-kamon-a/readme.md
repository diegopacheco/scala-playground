### Run Docker with Graphana and StatsD

docker run \
  --detach \
   --publish=80:80 \
   --publish=81:81 \
   --publish=8125:8125/udp \
   --publish=8126:8126 \
   --name kamon-grafana-dashboard \
   kamon/grafana_graphite

https://github.com/kamon-io/docker-grafana-graphite

### Run app with JVM ARGS for AspectJ Weaver

 -javaagent:C:\\Users\\diego\\.ivy2\\cache\\org.aspectj\\aspectjweaver\\jars\\aspectjweaver-1.8.2.jar

#### GOTO to see Graphana Dashboard on Akka Cluster

http://192.168.99.100/dashboard/db/kamon-dashboard

### Import the JSON Dashboards and them have fun
  * might need change the IPS(hardcoded to my box)
  * kamon.conf is pointing to my docker host that has a container running with graphana  