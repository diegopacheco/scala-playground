package com.github.diegopacheco.implictly

trait Searchable[T] {
  def uri(obj: T): String
}

case class Customer(taxCode: String, name: String, surname: String)
case class Policy(policyId: String, description: String)

object SearchableOps{
  implicit val searchableCustomer: Searchable[Customer] = new Searchable[Customer] {
    override def uri(customer: Customer): String = s"/customers/${customer.taxCode}"
  }
  implicit val searchablePolicy: Searchable[Policy] = new Searchable[Policy] {
    override def uri(policy: Policy): String = s"/policies/${policy.policyId}"
  }
}

// implicit
def searchWithImplicit[S](obj: S)(implicit searchable: Searchable[S]): String =
  searchable.uri(obj)

// implicitly
def searchWithContextBound[S: Searchable](obj: S): String = {
  val searchable = implicitly[Searchable[S]]
  searchable.uri(obj)
}

object ImplicitlyApp extends App {

  import SearchableOps._

  val customer = Customer("123456", "Will", "Smith")
  val uri = searchWithContextBound(customer)
  println(uri)
  println(searchWithImplicit(customer))

  val policy2 = Policy("09876", "A policy")
  val uri2 = searchWithContextBound(policy2)
  println(uri2)
  println(searchWithImplicit(policy2))

}
