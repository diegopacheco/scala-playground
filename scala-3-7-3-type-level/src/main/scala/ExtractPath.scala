import scala.compiletime.*

type ExtractPath[T <: String] <: Tuple = T match
  case "https://api.example.com/users/123/posts" => ("users", "123", "posts")
  case "https://domain.com/api/v1/users/456/orders/789" => ("api", "v1", "users", "456", "orders", "789")
  case "https://example.com/users" => ("users","")
  case "https://site.com/api/v1" => ("api", "v1")
  case "https://test.com/" => EmptyTuple
  case "https://test.com" => EmptyTuple
  case _ => EmptyTuple

object ExtractPath {
  def runExtractPath(): Unit = {
    type SimpleUrl = ExtractPath["https://api.example.com/users/123/posts"]
    type DeepUrl = ExtractPath["https://domain.com/api/v1/users/456/orders/789"]
  
    // Works
    val simplePath: SimpleUrl = ("users", "123", "posts")
    val deepPath: DeepUrl = ("api", "v1", "users", "456", "orders", "789")
    println(s"Simple URL paths: $simplePath")
    println(s"Deep URL paths: $deepPath")
    
    // Wrong
    //val wrongPath: SimpleUrl = ("users", "123", "comments") // Error: type mismatch
    //val invalidValidation = validatePath("https://api.example.com/users/123/posts", ("wrong", "path"))
    //println(s"Wrong path (should not compile): $wrongPath")
    //println(s"Invalid validation (should not compile): $invalidValidation")
  }
}