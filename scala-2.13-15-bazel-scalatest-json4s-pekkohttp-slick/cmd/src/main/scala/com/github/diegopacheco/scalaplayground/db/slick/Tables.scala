package com.github.diegopacheco.scalaplayground.db.slick
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
  lazy val schema: profile.SchemaDescription = Companies.schema ++ Computers.schema

  /** Entity class storing rows of table Companies
   *  @param id Database column id SqlType(int4)
   *  @param name Database column name SqlType(varchar) */
  case class CompaniesRow(id: Int, name: String)
  /** GetResult implicit for fetching CompaniesRow objects using plain SQL queries */
  implicit def GetResultCompaniesRow(implicit e0: GR[Int], e1: GR[String]): GR[CompaniesRow] = GR{
    prs => import prs._
    (CompaniesRow.apply _).tupled((<<[Int], <<[String]))
  }
  /** Table description of table companies. Objects of this class serve as prototypes for rows in queries. */
  class Companies(_tableTag: Tag) extends profile.api.Table[CompaniesRow](_tableTag, "companies") {
    def * = ((id, name)).mapTo[CompaniesRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> (CompaniesRow.apply _).tupled((_1.get, _2.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4) */
    val id: Rep[Int] = column[Int]("id")
    /** Database column name SqlType(varchar) */
    val name: Rep[String] = column[String]("name")
  }
  /** Collection-like TableQuery object for table Companies */
  lazy val Companies = new TableQuery(tag => new Companies(tag))

  /** Entity class storing rows of table Computers
   *  @param id Database column id SqlType(int4)
   *  @param name Database column name SqlType(varchar)
   *  @param manufacturerId Database column manufacturer_id SqlType(int4) */
  case class ComputersRow(id: Int, name: String, manufacturerId: Int)
  /** GetResult implicit for fetching ComputersRow objects using plain SQL queries */
  implicit def GetResultComputersRow(implicit e0: GR[Int], e1: GR[String]): GR[ComputersRow] = GR{
    prs => import prs._
    (ComputersRow.apply _).tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table computers. Objects of this class serve as prototypes for rows in queries. */
  class Computers(_tableTag: Tag) extends profile.api.Table[ComputersRow](_tableTag, "computers") {
    def * = ((id, name, manufacturerId)).mapTo[ComputersRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(manufacturerId))).shaped.<>({r=>import r._; _1.map(_=> (ComputersRow.apply _).tupled((_1.get, _2.get, _3.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4) */
    val id: Rep[Int] = column[Int]("id")
    /** Database column name SqlType(varchar) */
    val name: Rep[String] = column[String]("name")
    /** Database column manufacturer_id SqlType(int4) */
    val manufacturerId: Rep[Int] = column[Int]("manufacturer_id")
  }
  /** Collection-like TableQuery object for table Computers */
  lazy val Computers = new TableQuery(tag => new Computers(tag))
}
