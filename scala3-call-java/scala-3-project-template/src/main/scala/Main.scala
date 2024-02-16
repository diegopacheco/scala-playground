@main def hello(): Unit =
  import java.util.StringTokenizer

  val st = StringTokenizer("this is a test Scala3 calling Java 21 api.")
  while (st.hasMoreTokens) do
    println(st.nextToken())
