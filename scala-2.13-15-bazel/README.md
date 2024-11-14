### Bazel + Scala 2x

Versions:
```
Java: 1.8.0_372
Scala: 2.13.15
Bazel: 7.4.1
```

### Build

```bash
bazel build //lib:greeting
```
```
❯ bazel build //lib:greeting
INFO: Analyzed target //lib:greeting (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //lib:greeting up-to-date:
  bazel-bin/lib/greeting.jar
INFO: Elapsed time: 0.133s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
```

```bash
bazel build //cmd:runner
```
```
❯ bazel build //cmd:runner
INFO: Analyzed target //cmd:runner (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //cmd:runner up-to-date:
  bazel-bin/cmd/runner.jar
  bazel-bin/cmd/runner
INFO: Elapsed time: 0.279s, Critical Path: 0.22s
INFO: 2 processes: 1 internal, 1 worker.
INFO: Build completed successfully, 2 total actions
```

or if you want to build it all...
```bash
./build-all.sh
```
```
INFO: Analyzed 2 targets (0 packages loaded, 0 targets configured).
INFO: Found 2 targets...
INFO: Elapsed time: 0.071s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
```


### Run

```
bazel run //cmd:runner
```
```
❯ bazel run //cmd:runner
INFO: Analyzed target //cmd:runner (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //cmd:runner up-to-date:
  bazel-bin/cmd/runner.jar
  bazel-bin/cmd/runner
INFO: Elapsed time: 0.073s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
INFO: Running command line: bazel-bin/cmd/runner
Hello Scala 2.13.15 and Bazel!
```

### How to check sha256 or sha1 of a file?

sha256
```bash
wget https://repo1.maven.org/maven2/org/scala-lang/scala-compiler/2.13.5/scala-compiler-2.13.5.jar
sha256sum scala-compiler-2.13.5.jar
```
```
ea7423f3bc3673845d6d1c64335a4645abba0b0478ae00e15979915826ff6116
```

sha1
```bash
wget https://repo1.maven.org/maven2/org/scala-lang/scala-compiler/2.13.15/scala-compiler-2.13.15.jar
sha1sum scala-compiler-2.13.5.jar
```
```
348bf4d3dacc6905e9b85e451b13c816bed40938
```

### InteliJ IDEA

Bazel can be very anoying and complex.

InteliJ plugin is:
https://plugins.jetbrains.com/plugin/8609-bazel-for-intellij

However is very recommended you add this config before opening InteliJ:

```bash
~/.bazelrc
```
```
build --java_runtime_version=remotejdk_8
```

Them you need to import the project and point to:
````
.bazelproject
````