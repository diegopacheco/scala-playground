### Build

```
sbt compile
```

### Run
```
sbt run
```

### Result

```
TRULY DYNAMIC Scala 3 Compile-Time String Operations
===================================================

Empty string (compile-time proven):
  Reverse[""] = ''
  Length[""] = 0

Dynamic operations (work for ANY string):
  empty '':
    reverse: ''
    length: 0
  single char 'A':
    reverse: 'A'
    length: 1
  two chars 'Hi':
    reverse: 'iH'
    length: 2
  three chars 'abc':
    reverse: 'cba'
    length: 3
  five chars 'Hello':
    reverse: 'olleH'
    length: 5
  six chars 'Scala3':
    reverse: '3alacS'
    length: 6
  ten chars 'TypeSystem':
    reverse: 'metsySepyT'
    length: 10
  any string 'ThisIsAnyString':
    reverse: 'gnirtSynAsIsihT'
    length: 15

✓ TRULY DYNAMIC - works with any string at runtime!
✓ Compile-time optimizations for known patterns!
✓ Type-safe operations using Scala 3 match types!
✓ The Reverse and Length types are defined recursively!
```


