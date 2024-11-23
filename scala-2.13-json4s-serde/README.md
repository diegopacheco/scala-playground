### Build
```
sbt compile
```

### Run
```
sbt run
```

### Check dependencies

```bash
sbt dependencyTree
```
```
[info] scala-3-6-json4s-serde:scala-3-6-json4s-serde_2.13:1.0 [S]
[info]   +-org.json4s:json4s-native_2.13:4.0.7
[info]     +-org.json4s:json4s-core_2.13:4.0.7
[info]     | +-com.thoughtworks.paranamer:paranamer:2.8
[info]     | +-org.json4s:json4s-ast_2.13:4.0.7
[info]     | +-org.json4s:json4s-scalap_2.13:4.0.7
[info]     | 
[info]     +-org.json4s:json4s-native-core_2.13:4.0.7
[info]       +-org.json4s:json4s-ast_2.13:4.0.7
[info]       
[success] Total time: 1 s, completed Nov 22, 2024, 9:12:35 PM
```