package com.github.diegopacheco.scala.sandbox.fp.cats.datatypes

object KleisliConfigMain extends App {

  import cats.FlatMap
  import cats.Functor
  import cats.implicits._
  
  final case class Kleisli[F[_], Z, A](run: Z => F[A]) {
    def flatMap[B](f: A => Kleisli[F, Z, B])(implicit F: FlatMap[F]): Kleisli[F, Z, B] =
      Kleisli(z => F.flatMap(run(z))(a => f(a).run(z)))

    def map[B](f: A => B)(implicit F: Functor[F]): Kleisli[F, Z, B] =
      Kleisli(z => F.map(run(z))(f))

    def local[ZZ](f: ZZ => Z): Kleisli[F, ZZ, A] = Kleisli(f.andThen(run))
  }

  case class DbConfig(url: String, user: String, pass: String)
  trait Db
  object Db {
    val fromDbConfig: Kleisli[Option, DbConfig, Db] = ???
  }

  case class ServiceConfig(addr: String, port: Int)
  trait Service
  object Service {
    val fromServiceConfig: Kleisli[Option, ServiceConfig, Service] = ???
  }

  case class AppConfig(dbConfig: DbConfig, serviceConfig: ServiceConfig)

  class App(db: Db, service: Service)

  def appFromAppConfig: Kleisli[Option, AppConfig, App] =
    for {
      db <- Db.fromDbConfig.local[AppConfig](_.dbConfig)
      sv <- Service.fromServiceConfig.local[AppConfig](_.serviceConfig)
    } yield new App(db, sv)

   println(appFromAppConfig)   
}