### Build
```
sbt compile
```

### Run
```
sbt run
```

### Summary

1. IF I remove the Option[Int] from the ComputerModel case class it works.
2. IF I run with scala 2.12 it works. (and i see no scala 3x on the project in idea)
3. With scala 3.6 (3.3.x) it does not work.
4. I try to cross compile from 2.12 to 3 and did not work
5. I run sbt dependencyTree could not see scala 2x being loaded.
6. But on the idea project I see scala-library 2x being loaded and scala 3x. Which I think is freaking out json4s.

### Issue with Option[Int]

Error:
```
Caused by: org.json4s.MappingException: Can't find ScalaSig for class com.github.diegopacheco.json4sapp.ComputerModel
```
Full Stacktrace:
```
Exception in thread "main" java.lang.ExceptionInInitializerError
	at com.github.diegopacheco.json4sapp.Json4sApp.main(Json4sApp.scala)
Caused by: org.json4s.MappingException: Can't find ScalaSig for class com.github.diegopacheco.json4sapp.ComputerModel
	at org.json4s.reflect.package$.fail(package.scala:56)
	at org.json4s.reflect.ScalaSigReader$.$anonfun$7(ScalaSigReader.scala:81)
	at scala.Option.getOrElse(Option.scala:201)
	at org.json4s.reflect.ScalaSigReader$.findClass(ScalaSigReader.scala:81)
	at org.json4s.reflect.ScalaSigReader$.readConstructor(ScalaSigReader.scala:47)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.ctorParamType(Reflector.scala:162)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.$anonfun$6(Reflector.scala:152)
	at scala.collection.immutable.List.map(List.scala:247)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.ctorParamType(Reflector.scala:153)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.$anonfun$17(Reflector.scala:239)
	at scala.collection.immutable.ArraySeq.map(ArraySeq.scala:75)
	at scala.collection.immutable.ArraySeq.map(ArraySeq.scala:35)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.createConstructorDescriptors$$anonfun$3(Reflector.scala:247)
	at scala.collection.immutable.List.map(List.scala:247)
	at scala.collection.immutable.List.map(List.scala:79)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.createConstructorDescriptors(Reflector.scala:248)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.constructorsAndCompanion(Reflector.scala:180)
	at org.json4s.reflect.Reflector$ClassDescriptorBuilder.result(Reflector.scala:271)
	at org.json4s.reflect.Reflector$.createDescriptorWithFormats(Reflector.scala:87)
	at org.json4s.reflect.Reflector$.describeWithFormats$$anonfun$1(Reflector.scala:70)
	at org.json4s.reflect.Memo.apply(Memo.scala:12)
	at org.json4s.reflect.Reflector$.describeWithFormats(Reflector.scala:70)
	at org.json4s.Extraction$.decomposeObject$1(Extraction.scala:143)
	at org.json4s.Extraction$.internalDecomposeWithBuilder(Extraction.scala:260)
	at org.json4s.Extraction$.decomposeWithBuilder(Extraction.scala:84)
	at org.json4s.native.Serialization$.write(Serialization.scala:47)
	at org.json4s.native.Serialization$.write(Serialization.scala:40)
	at com.github.diegopacheco.json4sapp.Json4sApp$.<clinit>(Json4sApp.scala:57)
	... 1 more
```

### Check dependencies

```bash
sbt dependencyTree
```
```
sbt:scala-3.6-json4s-serde> dependencyTree
[info] default:scala-3-6-json4s-serde_3:1.0
[info]   +-org.json4s:json4s-ext_3:4.0.7
[info]   | +-joda-time:joda-time:2.10.14
[info]   | +-org.joda:joda-convert:2.2.2
[info]   | +-org.json4s:json4s-core_3:4.0.7
[info]   | | +-com.thoughtworks.paranamer:paranamer:2.8
[info]   | | +-org.json4s:json4s-ast_3:4.0.7
[info]   | | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | | 
[info]   | | +-org.json4s:json4s-scalap_3:4.0.7
[info]   | | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | | 
[info]   | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | 
[info]   | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | 
[info]   +-org.json4s:json4s-native_3:4.0.7
[info]   | +-org.json4s:json4s-core_3:4.0.7
[info]   | | +-com.thoughtworks.paranamer:paranamer:2.8
[info]   | | +-org.json4s:json4s-ast_3:4.0.7
[info]   | | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | | 
[info]   | | +-org.json4s:json4s-scalap_3:4.0.7
[info]   | | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | | 
[info]   | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | 
[info]   | +-org.json4s:json4s-native-core_3:4.0.7
[info]   | | +-org.json4s:json4s-ast_3:4.0.7
[info]   | | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | | 
[info]   | | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | | 
[info]   | +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   | 
[info]   +-org.scala-lang:scala3-library_3:3.6.1 [S]
[info]   
[success] Total time: 0 s, completed Nov 22, 2024, 8:25:30 PM
sbt:scala-3.6-json4s-serde> 
```