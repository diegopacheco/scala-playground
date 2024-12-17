### Build
```
sbt compile
```

### Run
```
sbt run
```

### Test

```
❯ sbt test
[info] welcome to sbt 1.10.6 (Amazon.com Inc. Java 21)
[info] loading settings for project global-plugins from build.sbt...
[info] loading global plugins from /home/diego/.sbt/1.0/plugins
[info] loading settings for project scala-3-6-junit-5-tests-build from plugins.sbt...
[info] loading project definition from /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/scala-3.6-junit-5-tests/project
[info] loading settings for project scala-3-6-junit-5-tests from build.sbt...
[info] set current project to scala-3.6-junit-5-tests (in build file:/mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/scala-3.6-junit-5-tests/)
[info] Test run started (JUnit Jupiter)
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testAdd() started
[info] Test add 2+3=5
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testDiv() started
[info] Test div 6/3=2
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testMod() started
[info] Test mod 7%3=1
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testMul() started
[info] Test nul 2*3=6
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testPow() started
[info] Test pow 2^3=8
[info] Test com.github.diegopacheco.scalaplayground.SimpleCalculatorTest#testSub() started
[info] Test sub 3-2=1
[info] Test run finished: 0 failed, 0 ignored, 6 total, 0.238s
[info] Passed: Total 6, Failed 0, Errors 0, Passed 6
[success] Total time: 2 s, completed Dec 16, 2024, 10:29:57 PM
```