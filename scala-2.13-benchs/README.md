## Scala 2.13.15 JMH Benchmarks

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
```
100k elements
```
[info] Benchmark               Mode  Cnt  Score   Error  Units
[info] ForBench.lambdaForeach  avgt   10  0.017 ± 0.007  ms/op
[info] ForBench.testFor        avgt   10  0.017 ± 0.006  ms/op
[info] ForBench.testTailRec    avgt   10  0.013 ± 0.001  ms/op
```