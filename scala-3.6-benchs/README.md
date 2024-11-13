## Scala 3.6.1 JMH Benchmarks

### Build
```
sbt compile
```

### Run
```
sbt run
```

### Results

```bash
./run-benchmarks.sh
```
1000 elements
```
[info] Benchmark               Mode  Cnt   Score    Error  Units
[info] ForBench.lambdaForeach  avgt   10  ≈ 10⁻⁴           ms/op
[info] ForBench.testFor        avgt   10  ≈ 10⁻⁴           ms/op
[info] ForBench.testTailRec    avgt   10  ≈ 10⁻⁴           ms/op
[success] Total time: 455 s (07:35), completed Nov 11, 2024, 10:37:08 PM
```
100k elements
```
[info] Benchmark               Mode  Cnt  Score   Error  Units
[info] ForBench.lambdaForeach  avgt   10  0.025 ± 0.023  ms/op
[info] ForBench.testFor        avgt   10  0.024 ± 0.021  ms/op
[info] ForBench.testTailRec    avgt   10  0.012 ± 0.001  ms/op
```