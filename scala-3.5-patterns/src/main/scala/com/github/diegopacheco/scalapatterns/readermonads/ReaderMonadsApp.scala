package com.github.diegopacheco.scalapatterns.readermonads

case class Reader[R, A](run: R => A) {
  def map[B](f: A => B): Reader[R, B] = Reader(r => f(run(r)))
  def flatMap[B](f: A => Reader[R, B]): Reader[R, B] = Reader(r => f(run(r)).run(r))
}

object Reader {
  def pure[R, A](a: A): Reader[R, A] = Reader(_ => a)
  def ask[R]: Reader[R, R] = Reader(r => r)
}

object ReaderMonadsApp extends App{

  case class Config(baseUrl: String, apiKey: String)

  def fetchData(endpoint: String): Reader[Config, String] = Reader { config =>
    s"Fetching data from ${config.baseUrl}/$endpoint with API key ${config.apiKey}"
  }

  def program: Reader[Config, String] = for {
    data1 <- fetchData("endpoint1")
    data2 <- fetchData("endpoint2")
  } yield s"$data1\n$data2"

  val config = Config("https://api.example.com", "my-secret-api-key")
  val result = program.run(config)

  println(result)

}
