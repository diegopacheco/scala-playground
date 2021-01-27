import diffson.jsonpatch.JsonPatch

object Main extends App {

  import diffson._
  import diffson.lcs._
  import diffson.circe._
  import diffson.jsonpatch.lcsdiff._

  import io.circe._
  import io.circe.parser._

  implicit val lcs = new Patience[Json]

  val decoder = Decoder[JsonPatch[Json]]
  val encoder = Encoder[JsonPatch[Json]]

  val json1 = parse("""{
                      |  "a": 1,
                      |  "b": true,
                      |  "c": ["test", "plop"]
                      |}""".stripMargin)

  val json2 = parse("""{
                      |  "a": 6,
                      |  "c": ["test2", "plop"],
                      |  "d": false
                      |}""".stripMargin)

  val patch =
    for {
      json1 <- json1
      json2 <- json2
    } yield diff(json1, json2)

  println(s"Result: ${patch.toOption.get.json}")

}
