import org.scalacheck.Properties

object StringSpecification extends Properties("String") {
 
  import org.scalacheck.Prop.forAll

  property("startsWith") = forAll { (a: String, b: String) =>
    (a + b).startsWith(a)
  }

  property("endsWith") = forAll { (a: String, b: String) =>
    (a + b).endsWith(b)
  }

  property("substring") = forAll { (a: String, b: String) =>
    (a + b).substring(a.length) == b
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a + b + c).substring(a.length, a.length + b.length) == b
  }
}   