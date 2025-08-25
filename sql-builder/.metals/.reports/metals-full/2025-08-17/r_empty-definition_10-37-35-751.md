error id: file://<WORKSPACE>/src/main/scala/Main.scala:`<none>`.
file://<WORKSPACE>/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -select/mkString.
	 -select/mkString#
	 -select/mkString().
	 -scala/Predef.select.mkString.
	 -scala/Predef.select.mkString#
	 -scala/Predef.select.mkString().
offset: 558
uri: file://<WORKSPACE>/src/main/scala/Main.scala
text:
```scala
@main def main():Unit =
  class QueryBuilder private (
    private val select: List[String] = Nil,
    private val from: Option[String] = None,
    private val where: List[String] = Nil
  ):
    def SELECT(fields: String*): QueryBuilder = 
      QueryBuilder(fields.toList, from, where)
      
    def FROM(table: String): QueryBuilder = 
      QueryBuilder(select, Some(table), where)
      
    def WHERE(condition: String): QueryBuilder = 
      QueryBuilder(select, from, where :+ condition)
      
    def build: String = 
      s"SELECT ${select.mkStri@@ng(", ")} FROM ${from.get} WHERE ${where.mkString(" AND ")}"

  object QueryBuilder:
    def apply(): QueryBuilder = new QueryBuilder()
    def apply(select: List[String], from: Option[String], where: List[String]): QueryBuilder = 
      new QueryBuilder(select, from, where)

  val query = QueryBuilder()
    .SELECT("name", "age")
    .FROM("users")
    .WHERE("age > 18")
    .WHERE("active = true")
    .build
  print(s"$query")

  val builder = QueryBuilder()
    .SELECT("name", "email")
    .FROM("contacts")
    .WHERE("subscribed = true")
    .build
  print(s"$builder")
  

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.