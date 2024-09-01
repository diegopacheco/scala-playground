### Build
```
sbt compile
```

### Run
```
sbt run
```

### Result

```
sbt:scala-3.5-pekko-simple> run
[info] running com.github.diegopacheco.scala35.pekko.App 
SLF4J(I): Connected with provider of type [ch.qos.logback.classic.spi.LogbackServiceProvider]
2024-09-01 00:02:34.884 [sbt-bg-threads-1] INFO  c.g.diegopacheco.scala35.pekko.App$ - Application starting...
2024-09-01 00:02:35.494 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.pekko.event.slf4j.Slf4jLogger - Slf4jLogger started
2024-09-01 00:02:35.880 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.remote.artery.ArteryTransport - Remoting started with transport [Artery tcp]; listening on address [pekko://ClusterSystem@127.0.0.1:17356] with UID [1570517868549733161]
2024-09-01 00:02:35.906 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Starting up, Pekko version [1.0.3] ...
2024-09-01 00:02:36.089 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Registered cluster JMX MBean [pekko:type=Cluster]
2024-09-01 00:02:36.089 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Started up successfully
2024-09-01 00:02:36.135 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  o.a.p.cluster.sbr.SplitBrainResolver - SBR started. Config: strategy [KeepMajority], stable-after [20 seconds], down-all-when-unstable [15 seconds], selfUniqueAddress [pekko://ClusterSystem@127.0.0.1:17356#1570517868549733161], selfDc [default].
2024-09-01 00:02:36.682 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.pekko.event.slf4j.Slf4jLogger - Slf4jLogger started
2024-09-01 00:02:36.702 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.remote.artery.ArteryTransport - Remoting started with transport [Artery tcp]; listening on address [pekko://ClusterSystem@127.0.0.1:17357] with UID [878290944438632026]
2024-09-01 00:02:36.705 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Starting up, Pekko version [1.0.3] ...
2024-09-01 00:02:36.713 [ClusterSystem-pekko.actor.default-dispatcher-3] WARN  org.apache.pekko.cluster.Cluster - Could not register Cluster JMX MBean with name=pekko:type=Cluster as it is already registered. If you are running multiple clusters in the same JVM, set 'pekko.cluster.jmx.multi-mbeans-in-same-jvm = on' in config
2024-09-01 00:02:36.713 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Started up successfully
2024-09-01 00:02:36.718 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.cluster.sbr.SplitBrainResolver - SBR started. Config: strategy [KeepMajority], stable-after [20 seconds], down-all-when-unstable [15 seconds], selfUniqueAddress [pekko://ClusterSystem@127.0.0.1:17357#878290944438632026], selfDc [default].
2024-09-01 00:02:36.760 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.pekko.event.slf4j.Slf4jLogger - Slf4jLogger started
2024-09-01 00:02:36.786 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.remote.artery.ArteryTransport - Remoting started with transport [Artery tcp]; listening on address [pekko://ClusterSystem@127.0.0.1:1402] with UID [9045598703864481810]
2024-09-01 00:02:36.787 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - Starting up, Pekko version [1.0.3] ...
2024-09-01 00:02:36.796 [ClusterSystem-pekko.actor.default-dispatcher-3] WARN  org.apache.pekko.cluster.Cluster - Could not register Cluster JMX MBean with name=pekko:type=Cluster as it is already registered. If you are running multiple clusters in the same JVM, set 'pekko.cluster.jmx.multi-mbeans-in-same-jvm = on' in config
2024-09-01 00:02:36.796 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - Started up successfully
2024-09-01 00:02:36.800 [ClusterSystem-pekko.actor.default-dispatcher-12] INFO  o.a.p.cluster.sbr.SplitBrainResolver - SBR started. Config: strategy [KeepMajority], stable-after [20 seconds], down-all-when-unstable [15 seconds], selfUniqueAddress [pekko://ClusterSystem@127.0.0.1:1402#9045598703864481810], selfDc [default].
[success] Total time: 3 s, completed Sep 1, 2024, 12:02:36 AM
2024-09-01 00:02:36.855 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Received InitJoin message from [Actor[pekko://ClusterSystem@127.0.0.1:17357/system/cluster/core/daemon/joinSeedNodeProcess-1#-1754163614]], but this node is not initialized yet
2024-09-01 00:02:36.856 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Received InitJoin message from [Actor[pekko://ClusterSystem@127.0.0.1:17356/system/cluster/core/daemon/firstSeedNodeProcess-1#632048202]], but this node is not initialized yet
2024-09-01 00:02:36.862 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Received InitJoin message from [Actor[pekko://ClusterSystem@127.0.0.1:1402/system/cluster/core/daemon/joinSeedNodeProcess-1#-1375333253]], but this node is not initialized yet
2024-09-01 00:02:36.865 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Received InitJoinNack message from [Actor[pekko://ClusterSystem@127.0.0.1:17357/system/cluster/core/daemon#-1488568766]] to [pekko://ClusterSystem@127.0.0.1:17356]
2024-09-01 00:02:36.878 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Node [pekko://ClusterSystem@127.0.0.1:17356] is JOINING itself (with roles [dc-default], version [0.0.0]) and forming new cluster
2024-09-01 00:02:36.881 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - is the new leader among reachable nodes (more leaders may exist)
2024-09-01 00:02:36.890 [ClusterSystem-pekko.actor.default-dispatcher-6] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Leader is moving node [pekko://ClusterSystem@127.0.0.1:17356] to [Up]
2024-09-01 00:02:36.900 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Received InitJoin message from [Actor[pekko://ClusterSystem@127.0.0.1:1402/system/cluster/core/daemon/joinSeedNodeProcess-1#-1375333253]] to [pekko://ClusterSystem@127.0.0.1:17356]
2024-09-01 00:02:36.900 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Sending InitJoinAck message from node [pekko://ClusterSystem@127.0.0.1:17356] to [Actor[pekko://ClusterSystem@127.0.0.1:1402/system/cluster/core/daemon/joinSeedNodeProcess-1#-1375333253]] (version [1.0.3])
2024-09-01 00:02:36.900 [ClusterSystem-pekko.actor.default-dispatcher-24] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17356
2024-09-01 00:02:36.906 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.cluster.sbr.SplitBrainResolver - This node is now the leader responsible for taking SBR decisions among the reachable nodes (more leaders may exist).
2024-09-01 00:02:36.949 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - Received InitJoinAck message from [Actor[pekko://ClusterSystem@127.0.0.1:17356/system/cluster/core/daemon#-1831440045]] to [pekko://ClusterSystem@127.0.0.1:1402]
2024-09-01 00:02:36.964 [ClusterSystem-pekko.actor.default-dispatcher-24] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Node [pekko://ClusterSystem@127.0.0.1:1402] is JOINING, roles [dc-default], version [0.0.0]
2024-09-01 00:02:37.006 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - Welcome from [pekko://ClusterSystem@127.0.0.1:17356]
2024-09-01 00:02:37.007 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17356
2024-09-01 00:02:37.126 [ClusterSystem-pekko.actor.default-dispatcher-24] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Leader is moving node [pekko://ClusterSystem@127.0.0.1:1402] to [Up]
2024-09-01 00:02:37.127 [ClusterSystem-pekko.actor.default-dispatcher-24] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:1402
2024-09-01 00:02:37.127 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  o.a.p.cluster.sbr.SplitBrainResolver - This node is not the leader any more and not responsible for taking SBR decisions.
2024-09-01 00:02:37.821 [ClusterSystem-pekko.actor.default-dispatcher-31] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:1402
2024-09-01 00:02:37.821 [ClusterSystem-pekko.actor.default-dispatcher-31] INFO  o.a.p.cluster.sbr.SplitBrainResolver - This node is now the leader responsible for taking SBR decisions among the reachable nodes (more leaders may exist).
2024-09-01 00:02:38.144 [ClusterSystem-pekko.actor.default-dispatcher-24] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - is no longer leader
2024-09-01 00:02:38.833 [ClusterSystem-pekko.actor.default-dispatcher-30] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - is the new leader among reachable nodes (more leaders may exist)
2024-09-01 00:02:41.879 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Received InitJoin message from [Actor[pekko://ClusterSystem@127.0.0.1:17357/system/cluster/core/daemon/joinSeedNodeProcess-1#-1754163614]] to [pekko://ClusterSystem@127.0.0.1:17356
2024-09-01 00:02:41.880 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Sending InitJoinAck message from node [pekko://ClusterSystem@127.0.0.1:17356] to [Actor[pekko://ClusterSystem@127.0.0.1:17357/system/cluster/core/daemon/joinSeedNodeProcess-1#-1754163614]] (version [1.0.3])
2024-09-01 00:02:41.905 [ClusterSystem-pekko.actor.default-dispatcher-13] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Received InitJoinAck message from [Actor[pekko://ClusterSystem@127.0.0.1:17356/system/cluster/core/daemon#-1831440045]] to [pekko://ClusterSystem@127.0.0.1:17357]
2024-09-01 00:02:41.908 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17356] - Node [pekko://ClusterSystem@127.0.0.1:17357] is JOINING, roles [dc-default], version [0.0.0]
2024-09-01 00:02:41.911 [ClusterSystem-pekko.actor.default-dispatcher-13] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:17357] - Welcome from [pekko://ClusterSystem@127.0.0.1:17356]
2024-09-01 00:02:41.911 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:1402
2024-09-01 00:02:41.912 [ClusterSystem-pekko.actor.default-dispatcher-28] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17356
2024-09-01 00:02:42.913 [ClusterSystem-pekko.actor.default-dispatcher-12] INFO  org.apache.pekko.cluster.Cluster - Cluster Node [pekko://ClusterSystem@127.0.0.1:1402] - Leader is moving node [pekko://ClusterSystem@127.0.0.1:17357] to [Up]
2024-09-01 00:02:42.914 [ClusterSystem-pekko.actor.default-dispatcher-12] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17357
2024-09-01 00:02:43.247 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17357
2024-09-01 00:02:43.851 [ClusterSystem-pekko.actor.default-dispatcher-3] INFO  c.g.d.scala35.pekko.ClusterListener$ - Member is Up: pekko://ClusterSystem@127.0.0.1:17357
```
