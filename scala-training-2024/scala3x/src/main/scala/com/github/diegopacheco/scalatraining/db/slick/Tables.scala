package com.github.diegopacheco.scalatraining.db.slick
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends Tables {
  val profile: slick.jdbc.JdbcProfile = slick.jdbc.PostgresProfile
}

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for
  // tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Products.schema ++ Sales.schema ++ Toll.schema

  /** Entity class storing rows of table Products
   *  @param id Database column id SqlType(int4)
   *  @param name Database column name SqlType(varchar)
   *  @param stock Database column stock SqlType(int4) */
  case class ProductsRow(id: Int, name: String, stock: Int)
  /** GetResult implicit for fetching ProductsRow objects using plain SQL queries */
  implicit def GetResultProductsRow(implicit e0: GR[Int], e1: GR[String]): GR[ProductsRow] = GR{
    prs => import prs._
    (ProductsRow.apply _).tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table products. Objects of this class serve as prototypes for rows in queries. */
  class Products(_tableTag: Tag) extends profile.api.Table[ProductsRow](_tableTag, "products") {
    def * = ((id, name, stock)).mapTo[ProductsRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(stock))).shaped.<>({r=>import r._; _1.map(_=> (ProductsRow.apply _).tupled((_1.get, _2.get, _3.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4) */
    val id: Rep[Int] = column[Int]("id")
    /** Database column name SqlType(varchar) */
    val name: Rep[String] = column[String]("name")
    /** Database column stock SqlType(int4) */
    val stock: Rep[Int] = column[Int]("stock")
  }
  /** Collection-like TableQuery object for table Products */
  lazy val Products = new TableQuery(tag => new Products(tag))

  /** Entity class storing rows of table Sales
   *  @param id Database column id SqlType(int4)
   *  @param date Database column date SqlType(date)
   *  @param productId Database column product_id SqlType(int4)
   *  @param quantity Database column quantity SqlType(int4) */
  case class SalesRow(id: Int, date: java.sql.Date, productId: Int, quantity: Int)
  /** GetResult implicit for fetching SalesRow objects using plain SQL queries */
  implicit def GetResultSalesRow(implicit e0: GR[Int], e1: GR[java.sql.Date]): GR[SalesRow] = GR{
    prs => import prs._
    (SalesRow.apply _).tupled((<<[Int], <<[java.sql.Date], <<[Int], <<[Int]))
  }
  /** Table description of table sales. Objects of this class serve as prototypes for rows in queries. */
  class Sales(_tableTag: Tag) extends profile.api.Table[SalesRow](_tableTag, "sales") {
    def * = ((id, date, productId, quantity)).mapTo[SalesRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(date), Rep.Some(productId), Rep.Some(quantity))).shaped.<>({r=>import r._; _1.map(_=> (SalesRow.apply _).tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4) */
    val id: Rep[Int] = column[Int]("id")
    /** Database column date SqlType(date) */
    val date: Rep[java.sql.Date] = column[java.sql.Date]("date")
    /** Database column product_id SqlType(int4) */
    val productId: Rep[Int] = column[Int]("product_id")
    /** Database column quantity SqlType(int4) */
    val quantity: Rep[Int] = column[Int]("quantity")
  }
  /** Collection-like TableQuery object for table Sales */
  lazy val Sales = new TableQuery(tag => new Sales(tag))

  /** Entity class storing rows of table Toll
   *  @param id Database column id SqlType(int4)
   *  @param date Database column date SqlType(date)
   *  @param carType Database column car_type SqlType(varchar)
   *  @param licensePlate Database column license_plate SqlType(varchar)
   *  @param tollAmount Database column toll_amount SqlType(int4) */
  case class TollRow(id: Int, date: java.sql.Date, carType: String, licensePlate: String, tollAmount: Int)
  /** GetResult implicit for fetching TollRow objects using plain SQL queries */
  implicit def GetResultTollRow(implicit e0: GR[Int], e1: GR[java.sql.Date], e2: GR[String]): GR[TollRow] = GR{
    prs => import prs._
    (TollRow.apply _).tupled((<<[Int], <<[java.sql.Date], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table toll. Objects of this class serve as prototypes for rows in queries. */
  class Toll(_tableTag: Tag) extends profile.api.Table[TollRow](_tableTag, "toll") {
    def * = ((id, date, carType, licensePlate, tollAmount)).mapTo[TollRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(date), Rep.Some(carType), Rep.Some(licensePlate), Rep.Some(tollAmount))).shaped.<>({r=>import r._; _1.map(_=> (TollRow.apply _).tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4) */
    val id: Rep[Int] = column[Int]("id")
    /** Database column date SqlType(date) */
    val date: Rep[java.sql.Date] = column[java.sql.Date]("date")
    /** Database column car_type SqlType(varchar) */
    val carType: Rep[String] = column[String]("car_type")
    /** Database column license_plate SqlType(varchar) */
    val licensePlate: Rep[String] = column[String]("license_plate")
    /** Database column toll_amount SqlType(int4) */
    val tollAmount: Rep[Int] = column[Int]("toll_amount")
  }
  /** Collection-like TableQuery object for table Toll */
  lazy val Toll = new TableQuery(tag => new Toll(tag))
}
