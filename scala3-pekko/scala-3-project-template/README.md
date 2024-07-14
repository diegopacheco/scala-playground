## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

### Result
```
sbt:Scala 3 Project Template> run
[info] running ActorHierarchyExperiments 
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[success] Total time: 1 s, completed Jul 13, 2024, 11:00:17 PM
First: Actor[pekko://testSystem/user/first-actor#705303069]
Second: Actor[pekko://testSystem/user/first-actor/second-actor#-1198200719]
sbt:Scala 3 Project Template> 
```