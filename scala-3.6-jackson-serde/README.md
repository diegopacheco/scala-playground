### Rationale

This POC aim to serialize/deserialize common types in scala 3x using Jackson.
Types here are:
* sealed trait + case object
* sealed trait + case class
* class with Option (Some and None)
* enum case with custom module
* enum with Enumeration
* class
* case class
* object

### Build
```
sbt compile
```

### Run
```
sbt run
```