### How to build?

```bash
./build.sh
```
```
❯ ./build.sh
INFO: Analyzed target //src/main/scala/lib:greeting (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //src/main/scala/lib:greeting up-to-date:
  bazel-bin/src/main/scala/lib/greeting.jar
INFO: Elapsed time: 0.083s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
INFO: Analyzed target //src/main/scala/cmd:runner (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //src/main/scala/cmd:runner up-to-date:
  bazel-bin/src/main/scala/cmd/runner.jar
  bazel-bin/src/main/scala/cmd/runner
INFO: Elapsed time: 0.086s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
```

### Run

```bash
./run.sh
```
```
❯ ./run.sh
Hi Scala 3.5 and Bazel!
```