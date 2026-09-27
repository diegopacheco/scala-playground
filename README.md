# scala-playground

Hands-on Scala POCs, from Scala 2.10 all the way to Scala 3.7 on Java 25. Each folder is a small standalone project, most of them run with `sbt run`.

## 🧬 Scala 3 Language Features

Scala 3 rewrote a lot of the language: new syntax, enums, givens, extension methods and export clauses.
These POCs look at one feature at a time.

* [scala3-enums](scala3-enums/) - Scala 3 enums
* [scala-3-cool-enums](scala-3-cool-enums/) - Enums with parameters, methods and pattern matching
* [recursive-enum](recursive-enum/) - Enums that reference themselves
* [enum-state-machine](enum-state-machine/) - A state machine modeled with enums
* [scala-3.5-given](scala-3.5-given/) - Given instances and using clauses
* [scala-3.5-newgivens-context](scala-3.5-newgivens-context/) - The new given syntax and context bounds
* [scala3-anti-given](scala3-anti-given/) - When givens hurt more than they help
* [scala-3-6-given-conversions](scala-3-6-given-conversions/) - Given conversions from Option to Optional
* [implicitly-3.5](implicitly-3.5/) - implicitly vs summon
* [scala3-implicit-conversion-danger-to-evil-zone](scala3-implicit-conversion-danger-to-evil-zone/) - Why implicit conversions are dangerous
* [scala3-extension-evil](scala3-extension-evil/) - Extension methods and their pitfalls
* [scala-using-fun](scala-using-fun/) - The using clause
* [scala3-case-classes-fun](scala3-case-classes-fun/) - Case classes
* [scala3-companion-objects](scala3-companion-objects/) - Companion objects
* [scala3-singleton](scala3-singleton/) - Singleton objects
* [scala3-trait-parameters](scala3-trait-parameters/) - Traits with parameters
* [traits-scala3](traits-scala3/) - Traits in Scala 3
* [scala3-tuples](scala3-tuples/) - Tuple operations
* [scala-3.5-named-tuples](scala-3.5-named-tuples/) - Named tuples
* [scala3-all-string-interpolators](scala3-all-string-interpolators/) - s, f, raw and custom string interpolators
* [multiline-scala3-str](multiline-scala3-str/) - Multiline strings
* [scala-3.5-binary-integerliterals](scala-3.5-binary-integerliterals/) - Binary integer literals
* [scala-3.5-multi-word-methods](scala-3.5-multi-word-methods/) - Method names with spaces using backticks
* [scala-3.5-backedin-setters](scala-3.5-backedin-setters/) - Getter and setter pairs with `value_=`
* [scala-3.6-targetname](scala-3.6-targetname/) - @targetName for operators and type erasure clashes
* [scala-3.6-field-annotation](scala-3.6-field-annotation/) - Annotations on fields
* [scala-3.6-dropped-underscore-uninitialized](scala-3.6-dropped-underscore-uninitialized/) - `scala.compiletime.uninitialized` replacing `= _`
* [scala3-eta-expansion](scala3-eta-expansion/) - Automatic eta expansion
* [scala-3.6-eta-expansion](scala-3.6-eta-expansion/) - Eta expansion in Scala 3.6
* [scala3-methods-vs-functions](scala3-methods-vs-functions/) - Methods vs function values
* [scala3-by-name-by-value](scala3-by-name-by-value/) - By-name vs by-value parameters
* [scala3-short-functions](scala3-short-functions/) - Short lambda syntax
* [scala3-mapfunc](scala3-mapfunc/) - Mapping functions over collections
* [scala3-high-order-functions](scala3-high-order-functions/) - Higher-order functions
* [scala3-collections](scala3-collections/) - Collections basics
* [scala3-more-collection-methods](scala3-more-collection-methods/) - More collection methods
* [scala3-create-lists-creative](scala3-create-lists-creative/) - Many ways to build a List
* [loops-scala3](loops-scala3/) - Loops in Scala 3
* [control-flow-assigment-scala3](control-flow-assigment-scala3/) - Control flow as expressions
* [boundary-break-scala3](boundary-break-scala3/) - boundary and break
* [pattern-matcher-scala3](pattern-matcher-scala3/) - Pattern matching
* [scala3-advanced-pattern-matcher-case](scala3-advanced-pattern-matcher-case/) - Advanced pattern matching
* [scala3-pattern-tailrec](scala3-pattern-tailrec/) - Pattern matching with @tailrec
* [scala3-multiversal-equality](scala3-multiversal-equality/) - Multiversal equality with CanEqual
* [scala3-Null-null-Nil-None-Nothin-wat](scala3-Null-null-Nil-None-Nothin-wat/) - Null, null, Nil, None and Nothing side by side
* [scala3-threadUnsafe](scala3-threadUnsafe/) - @threadUnsafe lazy vals
* [scala-thread0unsafe](scala-thread0unsafe/) - @threadUnsafe under parallel updates
* [scala3-class-derivation](scala3-class-derivation/) - Type class derivation with `derives`
* [scala3-call-java](scala3-call-java/) - Calling Java from Scala 3
* [scala-3.6-java-scala-conversions](scala-3.6-java-scala-conversions/) - Java and Scala collection conversions
* [read-line-scala3](read-line-scala3/) - Reading from stdin
* [scala-partial-function](scala-partial-function/) - Partial functions
* [scala-pkge](scala-pkge/) - Package objects
* [scala3x-experimental-canthrow](scala-3x-experimental-canthrow/) - Experimental CanThrow checked exceptions
* [scala-3x-experimental-erased](scala-3x-experimental-erased/) - Experimental erased definitions
* [scala-3-anti-patterns](scala-3-anti-patterns/) - Common anti-patterns: mutability, strings, isInstanceOf

## 🔣 Type System & Type-Level Programming

The type system can catch bugs at compile time and even run computations there.
Union types, match types, opaque types and type lambdas all show up here.

* [scala3-union-types](scala3-union-types/) - Union types
* [scala3-match-union-types](scala3-match-union-types/) - Matching on union types
* [scala3-match-types](scala3-match-types/) - Match types
* [scala3-opaque-types](scala3-opaque-types/) - Opaque types
* [scala3-opaquetype-companion-object](scala3-opaquetype-companion-object/) - Opaque types with companion objects
* [scala3-structural-types](scala3-structural-types/) - Structural types
* [scala-3x-structuraltypes](scala-3x-structuraltypes/) - More structural types
* [scala3-generics](scala3-generics/) - Generics and variance
* [scala3-type-classes](scala3-type-classes/) - Type classes
* [scala-2.12-typeclasses](scala-2.12-typeclasses/) - Type classes in Scala 2.12
* [scala3-polymorphic-function-method-types](scala3-polymorphic-function-method-types/) - Polymorphic function types
* [scala3-advanced-type-system-type-lambdas](scala3-advanced-type-system-type-lambdas/) - Type lambdas
* [scala-3.5-higher-kinded-types](scala-3.5-higher-kinded-types/) - Higher-kinded types
* [scala-3-6-typesystems](scala-3-6-typesystems/) - Type system tour in Scala 3.6
* [scala-3x-typesystem-concise](scala-3x-typesystem-concise/) - A concise type system tour
* [scala-3.6-banana-or-nothing](scala-3.6-banana-or-nothing/) - Union types to return a value or nothing
* [type-system-scala](type-system-scala/) - Type system basics
* [scala3-type-system-comptime-ops](scala3-type-system-comptime-ops/) - Compile-time operations
* [scala3-type-system-fibonacci](scala3-type-system-fibonacci/) - Fibonacci computed by the compiler
* [scala3-type-system-refined-match](scala3-type-system-refined-match/) - Refined match types
* [scala3-type-system-state-machine](scala3-type-system-state-machine/) - A state machine checked by types
* [scala3-type-system-units](scala3-type-system-units/) - Units of measure in types
* [scala-3-typelevel-programing](scala-3-typelevel-programing/) - Type-level programming
* [scala-3-7-3-type-level](scala-3-7-3-type-level/) - Type-level programming in Scala 3.7.3
* [typelevel-state-machine](typelevel-state-machine/) - Type-level state machine
* [compile-time-validation](compile-time-validation/) - Validating values at compile time
* [multi-type-safe-map](multi-type-safe-map/) - A heterogeneous map that stays type safe
* [typesafe-dsl-inflix-method](typesafe-dsl-inflix-method/) - A type-safe DSL with infix methods
* [sql-builder](sql-builder/) - An immutable SQL query builder DSL
* [scala-3-6-ids-poc](scala-3-6-ids-poc/) - Typed IDs and safe envelopes with encryption
* [scala-3.7-iron-refinement](scala-3.7-iron-refinement/) - Refinement types with Iron
* [scala-3.5-typelevel-literally](scala-3.5-typelevel-literally/) - Compile-time checked literals with literally

## 🧠 Functional Programming

Functional programming in Scala means immutable data, pure functions and monads.
Errors become values instead of exceptions.

* [scala3-functional-programing](scala3-functional-programing/) - Functional programming basics
* [scala-some-fp](scala-some-fp/) - Try, flatMap and for-comprehensions
* [functional-scripts](functional-scripts/) - Monads as Scala scripts
* [monad-box-container](monad-box-container/) - A Box monad that works in for-comprehensions
* [scala-3.6-great-monad](scala-3.6-great-monad/) - Chaining Option as a monad
* [scala3-either-right-left](scala3-either-right-left/) - Either, Right and Left
* [scala-3-left-right-exceptions](scala-3-left-right-exceptions/) - Either instead of exceptions
* [scala-fp-error-handling](scala-fp-error-handling/) - Functional error handling
* [railroad-oriented-error-handling-scala-3x](railroad-oriented-error-handling-scala-3x/) - Railroad-oriented error handling
* [scala-3-6-future-combine](scala-3-6-future-combine/) - Combining Futures
* [scala-3-6-future-flatmap-recover](scala-3-6-future-flatmap-recover/) - Future flatMap and recover
* [monadless-fun](monadless-fun/) - Monadless: direct style over monads
* [scala-2.13-pipes-fun](scala-2.13-pipes-fun/) - Pipe operators with scala.util.chaining
* [cake](cake/) - The cake pattern with traits
* [idiomatic-scala](idiomatic-scala/) - Idiomatic Scala: Option over exceptions, lazy filters, config
* [scala-way](scala-way/) - Java way vs Scala way
* [fizz-buzz-noif](fizz-buzz-noif/) - FizzBuzz with no if
* [scala-3.6-kyo-basics](scala-3.6-kyo-basics/) - Kyo algebraic effects

## 🐱 Typelevel, Cats & Friends

Cats and Scalaz bring type classes like Functor, Monad and Traverse.
Cats Effect and FS2 add pure IO and streaming on top.

* [cats-scala-fp](cats-scala-fp/) - Cats basics
* [bag-of-cats](bag-of-cats/) - Cats monads, semigroupal, apply and traverse
* [scala-3-cats-eitherT](scala-3-cats-eitherT/) - Cats EitherT monad transformer
* [scala-3.5-typelevel-cats-effect-3x](scala-3.5-typelevel-cats-effect-3x/) - Cats Effect 3
* [scala-3.5-typelevel-fs2](scala-3.5-typelevel-fs2/) - FS2 streams
* [scala-3.5-typelevel-mouse](scala-3.5-typelevel-mouse/) - Mouse syntax extensions
* [scala-3.5-typelevel-vault](scala-3.5-typelevel-vault/) - Vault type-safe heterogeneous maps
* [scalaz](scalaz/) - Scalaz basics
* [scalaz-code-for-terrans](scalaz-code-for-terrans/) - Scalaz explained for everyone
* [scala-3.5-scalaz](scala-3.5-scalaz/) - Scalaz on Scala 3.5
* [shapeless-fun](shapeless-fun/) - Shapeless generic programming
* [scala-3.5-monocle](scala-3.5-monocle/) - Monocle optics
* [scala-3.5-enumeratum](scala-3.5-enumeratum/) - Enumeratum enums
* [scala-3.7-bourbon-validation](scala-3.7-bourbon-validation/) - Bourbon validation
* [accord-wix-fun](accord-wix-fun/) - Wix Accord validation

## 🌀 ZIO

ZIO is an effect system with typed errors, fibers and built-in dependency injection.
Its ecosystem covers HTTP, JDBC, Redis, JSON and streams.

* [scala-3.5-zio](scala-3.5-zio/) - ZIO basics
* [zio-di](zio-di/) - Dependency injection with ZLayer
* [zio-di-advanced](zio-di-advanced/) - Advanced ZLayer wiring
* [zio-di-type-system](zio-di-type-system/) - DI checked by the type system
* [zio-di-type-system-odersky](zio-di-type-system-odersky/) - DI with the type system, Odersky style
* [zio-stm](zio-stm/) - Software transactional memory
* [zio-zstate](zio-zstate/) - ZState
* [zstream-zsink](zstream-zsink/) - ZStream and ZSink
* [scala-3.6.1-zio-stream-2x](scala-3.6.1-zio-stream-2x/) - ZIO Streams 2
* [scala-3.6.1-zio-batch](scala-3.6.1-zio-batch/) - Batch processing with ZIO
* [scala-3.6.1-zio-query](scala-3.6.1-zio-query/) - ZIO Query batching and caching
* [scala-3.6.1-zio-query-postgres](scala-3.6.1-zio-query-postgres/) - ZIO Query over Postgres
* [scala-3.6.1-zio-jdbc](scala-3.6.1-zio-jdbc/) - ZIO JDBC
* [scala-3.6.1-zio-redis](scala-3.6.1-zio-redis/) - ZIO Redis
* [scala-3.6.1-zio-json](scala-3.6.1-zio-json/) - ZIO JSON
* [scala-3.5-zio-http](scala-3.5-zio-http/) - ZIO HTTP
* [scala_3_zio-http](scala_3_zio-http/) - ZIO HTTP on Scala 3
* [scala-3.6.1-zio-http-netty](scala-3.6.1-zio-http-netty/) - ZIO HTTP on Netty

## 🎭 Akka & Pekko

Akka and its Apache fork Pekko are actor toolkits for concurrency and distribution.
Clusters, persistence, routers and reactive streams are all here.

* [akka](akka/) - Akka basics
* [akka-vending-machine](akka-vending-machine/) - A vending machine built with actors
* [akka-microkernel-playground](akka-microkernel-playground/) - Akka microkernel
* [akka-scala-gradle](akka-scala-gradle/) - Akka built with Gradle
* [akka-26-sbt14-fun](akka-26-sbt14-fun/) - Akka 2.6 with sbt 1.4
* [scala-akka-template](scala-akka-template/) - Akka project template
* [scala-akka-futures](scala-akka-futures/) - Akka and Futures
* [scala-akka-persistence](scala-akka-persistence/) - Akka Persistence
* [scala-akka-kamon-a](scala-akka-kamon-a/) - Akka metrics with Kamon
* [scala-akka-multi-jvm-testing-fun](scala-akka-multi-jvm-testing-fun/) - Multi-JVM tests for Akka
* [akka-cluster-playground](akka-cluster-playground/) - Akka cluster
* [akka-2.2.0-cluster-playground](akka-2.2.0-cluster-playground/) - Akka 2.2 cluster
* [akka-2.3.9-scala.2.11.5-cluster](akka-2.3.9-scala.2.11.5-cluster/) - Akka 2.3 cluster on Scala 2.11
* [akka-2.5.6-cluster-docker](akka-2.5.6-cluster-docker/) - Akka 2.5 cluster in Docker
* [scala-akka-cluster](scala-akka-cluster/) - Akka cluster basics
* [scala-akka-cluster-aware-routers](scala-akka-cluster-aware-routers/) - Cluster-aware routers
* [scala-akka-cluster-frontend-backend](scala-akka-cluster-frontend-backend/) - Frontend and backend cluster nodes
* [scala-akka-clusterdistpubsub-a](scala-akka-clusterdistpubsub-a/) - Distributed pub/sub
* [scala_11_akka_23_full_playground](scala_11_akka_23_full_playground/) - Akka 2.3 on Scala 2.11: cluster, supervisors and more
* [akka-streams-patterns](akka-streams-patterns/) - Akka Streams patterns
* [akkastreams-2.5.7-fun](akkastreams-2.5.7-fun/) - Akka Streams 2.5
* [scala-akka-streams-a](scala-akka-streams-a/) - Akka Streams basics
* [akka-http-fun](akka-http-fun/) - Akka HTTP
* [akka-http-streams-fun](akka-http-streams-fun/) - Akka HTTP with streams
* [scala3-akka-http](scala3-akka-http/) - Akka HTTP on Scala 3 with an in-memory users store
* [scala3-pekko](scala3-pekko/) - Pekko on Scala 3
* [scala-3.5-pekko-simple](scala-3.5-pekko-simple/) - Pekko actors
* [scala-3.5-pekko-http](scala-3.5-pekko-http/) - Pekko HTTP

## 🌐 Web & HTTP Frameworks

Scala has many ways to serve HTTP, from full-stack Play to tiny functional libraries.
Each POC builds a small service with one of them.

* [play-rest-json](play-rest-json/) - Play REST API with JSON
* [play-client-json-rest](play-client-json-rest/) - Play WS client for a JSON REST API
* [play-akka](play-akka/) - Play with Akka actors
* [play-jasper](play-jasper/) - Play with JasperReports
* [play-scala-slick31](play-scala-slick31/) - Play with Slick 3.1
* [play-2.5-scala-app-sandbox](play-2.5-scala-app-sandbox/) - Play 2.5 app
* [play-2.9-scala-jdk21](play-2.9-scala-jdk21/) - Play 2.9 on JDK 21
* [http4s-fun](http4s-fun/) - http4s
* [tapir-http4s](tapir-http4s/) - Tapir endpoints on http4s
* [tapir-netty](tapir-netty/) - Tapir endpoints on Netty
* [caliban-graphql-fun](caliban-graphql-fun/) - Caliban GraphQL
* [finch-fun](finch-fun/) - Finch on Finagle
* [finatra_1.5.4_app](finatra_1.5.4_app/) - Finatra 1.5
* [twitter-finagle-playground-fun](twitter-finagle-playground-fun/) - Twitter Finagle
* [tumblr-colossus-fun](tumblr-colossus-fun/) - Tumblr Colossus HTTP and telnet servers
* [scalatra-simple](scalatra-simple/) - Scalatra basics
* [scalatra-playground](scalatra-playground/) - Scalatra app
* [vertx-scala-fun](vertx-scala-fun/) - Vert.x with Scala
* [lagom-fun](lagom-fun/) - Lagom microservices
* [swagger-playground](swagger-playground/) - Swagger API docs
* [scala-karyon-sbt-native-packager](scala-karyon-sbt-native-packager/) - Netflix Karyon packaged with sbt-native-packager

## 🍃 Spring Boot & Observability

Scala 3 runs fine on Spring Boot, including Boot 4 on Java 25.
Some POCs add Prometheus, Grafana and k6 load tests to see how the app behaves under load.

* [spring-boot-fun](spring-boot-fun/) - Spring Boot with Scala
* [scala-2.10-spring-3.2-playground](scala-2.10-spring-3.2-playground/) - Spring 3.2 on Scala 2.10
* [scala-3.5-spring-boot-3.3-java-21](scala-3.5-spring-boot-3.3-java-21/) - Spring Boot 3.3 on Java 21
* [scala-3.5-spring-boot-3.3-java-21-retry](scala-3.5-spring-boot-3.3-java-21-retry/) - Spring Retry on Boot 3.3
* [scala-3.6-spring-boot-3.4-data-jdbc](scala-3.6-spring-boot-3.4-data-jdbc/) - Spring Data JDBC with custom converters
* [scala-3.6-pekko-spring-boot-3.4.x](scala-3.6-pekko-spring-boot-3.4.x/) - Pekko inside Spring Boot 3.4
* [scala-3-sb-4-java-25-fun](scala-3-sb-4-java-25-fun/) - Spring Boot 4 on Java 25
* [scala-3.7-spring-boot-3.5-netty-metrics-grafana-k6](scala-3.7-spring-boot-3.5-netty-metrics-grafana-k6/) - Boot 3.5 on Netty with metrics, Grafana and k6
* [scala-3.7-spring-boot-3.5-netty-multi-workers-metrics-grafana-k6](scala-3.7-spring-boot-3.5-netty-multi-workers-metrics-grafana-k6/) - Multi-module Netty workers with metrics, Grafana and k6
* [scala-3.7-spring-boot-3.5-virtual-metrics-grafana-k6](scala-3.7-spring-boot-3.5-virtual-metrics-grafana-k6/) - Boot 3.5 on Tomcat with virtual threads, Grafana and k6
* [scala-3x-sb-netty-prometheus-cardinality-test](scala-3x-sb-netty-prometheus-cardinality-test/) - JUnit 5 test that catches high-cardinality Prometheus metrics
* [scala-3x-sb-netty-prometheus-cardinality-test-original](scala-3x-sb-netty-prometheus-cardinality-test-original/) - The original cardinality test with chaos
* [tinylog-fun](tinylog-fun/) - tinylog logging

## 🗄️ Databases & Persistence

Type-safe database access, from Slick's collection-style queries to Skunk's pure Postgres driver.
Also NoSQL stores, search and caches.

* [scala-slick-fun](scala-slick-fun/) - Slick basics
* [slick-codegen](slick-codegen/) - Slick code generation from a schema
* [scala-3.5-slick](scala-3.5-slick/) - Slick on Scala 3.5
* [scala-3.6-slick-postgresql](scala-3.6-slick-postgresql/) - Slick with PostgreSQL
* [scala-3.6-pekko-json4s-slick-postgresql](scala-3.6-pekko-json4s-slick-postgresql/) - Pekko HTTP, json4s, Slick and PostgreSQL
* [scala-3.5-pg-skunk](scala-3.5-pg-skunk/) - Skunk Postgres driver
* [scala-3.5-pg-skunk-query](scala-3.5-pg-skunk-query/) - Skunk queries
* [scala-3.5-pg-skunk-tx](scala-3.5-pg-skunk-tx/) - Skunk transactions
* [scala-couchdb-sbt](scala-couchdb-sbt/) - CouchDB
* [scala-dynamodb-aws](scala-dynamodb-aws/) - AWS DynamoDB
* [scala-solr-sbt](scala-solr-sbt/) - Apache Solr
* [shade-memcached-fun](shade-memcached-fun/) - Shade Memcached client

## 📦 JSON & Serialization

Turning case classes into JSON, binary or CSV and back again.
Each library makes different trade-offs on derivation, speed and Scala 3 support.

* [circe-fun](circe-fun/) - Circe
* [scala3x-json-circe](scala3x-json-circe/) - Circe on Scala 3
* [scala-2.13-json4s-serde](scala-2.13-json4s-serde/) - json4s on Scala 2.13
* [scala-3.5-json4s](scala-3.5-json4s/) - json4s on Scala 3.5
* [scala-3.6-json4s-serde](scala-3.6-json4s-serde/) - json4s serde on Scala 3.6
* [scala-3.6-jackson-serde](scala-3.6-jackson-serde/) - Jackson with common Scala 3 types
* [scala-3.6.1-gson](scala-3.6.1-gson/) - Gson
* [generic-json-enum](generic-json-enum/) - Generic JSON for enums
* [diffson-fun](diffson-fun/) - Diffson JSON diff and patch
* [scodec-simple](scodec-simple/) - scodec binary codecs
* [scala-csv-to-json](scala-csv-to-json/) - CSV to JSON

## ⚡ Concurrency & Reactive

Futures, queues, ring buffers and reactive variables.
Different ways to run work at the same time without losing your mind.

* [scala3-concurrency-futures](scala3-concurrency-futures/) - Futures in Scala 3
* [monix-fun](monix-fun/) - Monix tasks and observables
* [lmax-disruptor-fun](lmax-disruptor-fun/) - LMAX Disruptor ring buffer
* [hawt-dispatch-fun](hawt-dispatch-fun/) - HawtDispatch counters, semaphores and continuations
* [libchain-fun](libchain-fun/) - Go-like channels with jchan
* [reactify-fun](reactify-fun/) - Reactify reactive vars and channels
* [apollo-fun](apollo-fun/) - Apache ActiveMQ Apollo queues and topics
* [scala-3x-submission-wrapper-internal-queue](scala-3x-submission-wrapper-internal-queue/) - An adapter that wraps task submission with an internal queue

## 🔗 Integration

Apache Camel routes messages between systems with a simple DSL.

* [camel](camel/) - Apache Camel
* [scala-camel-simple](scala-camel-simple/) - Camel basics
* [akka-camel](akka-camel/) - Akka Camel
* [scala-akka-camel-c](scala-akka-camel-c/) - Akka Camel consumers

## 🔧 Build, Tooling & Runtimes

sbt, Bazel and Gradle builds, fat jars, native binaries and the browser.
Also macros, reflection and parsers.

* [sbt](sbt/) - sbt basics
* [sbt-commands](sbt-commands/) - Custom sbt commands
* [sbt-scala-seed](sbt-scala-seed/) - sbt seed project
* [sbt-1.4.0-fun](sbt-1.4.0-fun/) - sbt 1.4
* [scala-2.13.4-sbt-1.4.0-fun](scala-2.13.4-sbt-1.4.0-fun/) - Scala 2.13.4 on sbt 1.4
* [fatjar-assembly-sbt-1.5.0](fatjar-assembly-sbt-1.5.0/) - Fat jar with sbt-assembly
* [activator-fun](activator-fun/) - Typesafe Activator
* [scala-2.13-bazel](scala-2.13-bazel/) - Scala 2.13 on Bazel
* [scala-2.13-15-bazel](scala-2.13-15-bazel/) - Scala 2.13.15 on Bazel
* [scala-2.13-15-bazel-scalatest](scala-2.13-15-bazel-scalatest/) - Bazel with ScalaTest
* [scala-2.13-15-bazel-scalatest-3rdypartydeps](scala-2.13-15-bazel-scalatest-3rdypartydeps/) - Bazel with third-party deps
* [scala-2.13-15-bazel-scalatest-json4s-pekkohttp](scala-2.13-15-bazel-scalatest-json4s-pekkohttp/) - Bazel with json4s and Pekko HTTP
* [scala-2.13-15-bazel-scalatest-json4s-pekkohttp-slick](scala-2.13-15-bazel-scalatest-json4s-pekkohttp-slick/) - Bazel with json4s, Pekko HTTP and Slick
* [scala-3.5-bazel](scala-3.5-bazel/) - Scala 3.5 on Bazel
* [scala-3.5-dep](scala-3.5-dep/) - Scala CLI script with dependencies
* [scala-native](scala-native/) - Scala Native
* [scala-native-comp-aheadoftime](scala-native-comp-aheadoftime/) - Scala Native ahead-of-time compilation
* [scala-js-1.0-fun](scala-js-1.0-fun/) - Scala.js 1.0
* [scalafx-fun](scalafx-fun/) - ScalaFX desktop UI
* [javaToScala](javaToScala/) - Java to Scala source converter
* [scala-3.6.1-code-trick-poc](scala-3.6.1-code-trick-poc/) - Fast Java to Scala migration tricks
* [scala-macros-fun](scala-macros-fun/) - Macros
* [scala-uses-macros](scala-uses-macros/) - Using macros from another module
* [scala-2.13-runtime-reification](scala-2.13-runtime-reification/) - Runtime reification in Scala 2.13
* [scala-3.6-runtime-reification](scala-3.6-runtime-reification/) - Runtime reification in Scala 3.6
* [scala-3.6-reflections](scala-3.6-reflections/) - Reflection in Scala 3.6
* [jodd-classloader-runtime](jodd-classloader-runtime/) - Loading classes at runtime with Jodd
* [ParserCombinatorsFun](ParserCombinatorsFun/) - Parser combinators
* [parser-combinator-3.5](parser-combinator-3.5/) - Parser combinators on Scala 3.5
* [parboiled-scala-2x](parboiled-scala-2x/) - Parboiled2 PEG parser
* [clip-args-fun](clip-args-fun/) - Command-line args parsing
* [scala-3.7-better-files](scala-3.7-better-files/) - better-files IO
* [chronoscala-fun](chronoscala-fun/) - Chronoscala dates and times
* [uuid4s-fun](uuid4s-fun/) - uuid4s UUIDs

## 🧪 Testing & Benchmarks

Unit tests, integration tests with containers, load tests and micro benchmarks.

* [scala-test](scala-test/) - ScalaTest basics
* [scala-test-testing](scala-test-testing/) - More ScalaTest styles
* [scala-3.6-junit-5-tests](scala-3.6-junit-5-tests/) - JUnit 5 with Scala 3.6
* [testcontainers-scala](testcontainers-scala/) - Testcontainers
* [gatling-elassandra](gatling-elassandra/) - Gatling load test on Elassandra
* [scala-2.13-benchs](scala-2.13-benchs/) - Benchmarks on Scala 2.13
* [scala-3.6-benchs](scala-3.6-benchs/) - Benchmarks on Scala 3.6

## 🎯 Katas, Games & Training

Small problems solved in Scala, a game and training material.

* [scala-99-problems-but-aintone](scala-99-problems-but-aintone/) - S-99 Ninety-Nine Scala Problems migrated to Scala 3.6.1
* [scala-3-proof-of-9](scala-3-proof-of-9/) - Proof of nine divisibility checks
* [asg-simulator](asg-simulator/) - Auto scaling group simulator
* [snake-game-scala-3.6](snake-game-scala-3.6/) - Snake game
* [scala-training-2024](scala-training-2024/) - Scala training exercises with tests

## 🕰️ Playgrounds by Version

General sandboxes, one per Scala version, from 2.10 to 3.7.

* [scala_2.10_da_prog_funcional_as_novas_features_java](scala_2.10_da_prog_funcional_as_novas_features_java/) - Functional programming talk, Java side (pt-BR)
* [scala_2.10_da_prog_funcional_as_novas_features_scala](scala_2.10_da_prog_funcional_as_novas_features_scala/) - Functional programming talk, Scala side (pt-BR)
* [scala-2.10-playground](scala-2.10-playground/) - Scala 2.10
* [scala.211.playground](scala.211.playground/) - Scala 2.11
* [scala-playground](scala-playground/) - Early Scala scripts and monads
* [scala-2x-playground](scala-2x-playground/) - Scala 2.x
* [jdk-15-scala-2-13-sbt-1-4-simple](jdk-15-scala-2-13-sbt-1-4-simple/) - Scala 2.13 on JDK 15
* [scala-3.3.0-fun](scala-3.3.0-fun/) - Scala 3.3
* [scala-3.5-basic](scala-3.5-basic/) - Scala 3.5
* [scala-3.5-patterns](scala-3.5-patterns/) - Design patterns in Scala 3.5
* [scala-3-hello](scala-3-hello/) - Scala 3 hello world
* [scala-3-7-3-java-25-hello-world](scala-3-7-3-java-25-hello-world/) - Scala 3.7.3 on Java 25
* [scala3-simple-fun](scala3-simple-fun/) - Simple Scala 3 sbt project
* [scala3-try-me](scala3-try-me/) - Scala 3 project template
* [scala-3-playground](scala-3-playground/) - Scala 3 features: intersection types and more
* [scala-3x-playground](scala-3x-playground/) - Practical Scala 3: opaque types, parameter untupling, new collection functions
