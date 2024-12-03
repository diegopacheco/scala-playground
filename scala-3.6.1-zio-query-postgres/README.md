### Build
```
sbt compile
```

### Run
```
sbt run
```
```
query IDS: Range 1 to 10
2. Request: Batch
got IDS: List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
Postgres Query: Sql(SELECT id, name FROM products WHERE id IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
Query Result: List((1,coke), (2,pepsi), (3,fanta))
Query execution time: 147 ms
```
