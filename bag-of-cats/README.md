### Build
```
sbt compile
```

### Run
```
sbt run
```

### Functional Programing Principles

* Pure Functions
* Referential Transparency
* Type Classes (Eq, Show, Order, ...)
* Laziness
* Pattern Matching

### Functional Programing Patterns

* Monads
  * State Monad
  * Reader Monad
  * Writer Monad
  * Transform Monad
  * Free Monad
  * IO Monad
* Functors
* Applicatives
* Monoids
* Semigroups
* Bifunctors
* Kleisli
* Traverse
* Foldable

### Pattern and Function to be implemented

| Pattern         | Functions to Implement                  | Practical Use Cases                             |
|-----------------|-----------------------------------------|-------------------------------------------------|
| **Monads**      | `flatMap`, `map`, `pure`                | Composition, Error Handling, Sequencing         |
| **State Monad** | `get`, `set`, `modify`, `inspect`       | State Management, Configuration, Simulation     |
| **Reader Monad**| `ask`, `map`, `flatMap`                 | Dependency Injection, Configuration, Contextual Computation |
| **Writer Monad**| `tell`, `listen`, `map`, `flatMap`      | Logging, Accumulating Results, Tracing          |
| **Transform Monad** | `transform`, `map`, `flatMap`      | Combining Effects, Layering Abstractions        |
| **Free Monad**  | `pure`, `flatMap`, `foldMap`            | Interpreters, DSLs, Modularity                  |
| **IO Monad**    | `pure`, `flatMap`, `map`, `unsafeRun`   | Side Effects, Asynchronous Programming, Resource Management |
| **Functors**    | `map`                                   | Data Transformation, Composition                |
| **Applicatives**| `ap`, `pure`, `map`                     | Parallel Computation, Validation, Composition   |
| **Monoids**     | `combine`, `empty`                      | Aggregation, Summarization, Default Values      |
| **Semigroups**  | `combine`                               | Aggregation, Merging, Combining Results         |
| **Bifunctors**  | `bimap`, `leftMap`, `rightMap`          | Error Handling, Data Transformation, Composition|
| **Kleisli**     | `run`, `map`, `flatMap`                 | Function Composition, Pipelines, Dependency Injection |
| **Traverse**    | `traverse`, `sequence`                  | Iteration, Transformation, Effectful Traversal  |
| **Foldable**    | `foldLeft`, `foldRight`, `reduce`       | Aggregation, Summarization, Iteration           |