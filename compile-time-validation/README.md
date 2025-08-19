### Build

```
sbt compile
```

### Run
```
sbt run
```

### Result

invalid email without `@`
```scala
[info] compiling 1 Scala source to /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/compile-time-validation/target/scala-3.6.3/classes ...
[error] -- Error: /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/compile-time-validation/src/main/scala/Main.scala:22:24 
[error] 22 |  val validEmail = Email("usersdomain.com")
[error]    |                   ^^^^^^^^^^^^^^^^^^^^^^^^
[error]    |                  Email not in the predefined valid list at compile time
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 3 s, completed 17 de ago. de 2025 10:53:04
```

valid email
```scala
❯ ./run.sh
[info] welcome to sbt 1.10.7 (Amazon.com Inc. Java 21)
[info] loading settings for project global-plugins from build.sbt...
[info] loading global plugins from /home/diego/.sbt/1.0/plugins
[info] loading settings for project compile-time-validation-build from plugins.sbt...
[info] loading project definition from /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/compile-time-validation/project
[info] loading settings for project compile-time-validation from build.sbt...
[info] set current project to compile-time-validation (in build file:/mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/compile-time-validation/)
[info] compiling 1 Scala source to /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/compile-time-validation/target/scala-3.6.3/classes ...
[info] running main 
Valid email: user@sdomain.com
Domain: user@sdomain.com
Runtime validated email: another@example.com
[success] Total time: 4 s, completed 17 de ago. de 2025 10:53:36
```
