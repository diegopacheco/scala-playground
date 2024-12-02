package com.github.diegopacheco.scalaplayground.scala3xzio

import zio._
import zio.json._

case class Banana(curvature: Double)
object Banana {
  implicit val decoder: JsonDecoder[Banana] = DeriveJsonDecoder.gen[Banana]
  implicit val encoder: JsonEncoder[Banana] = DeriveJsonEncoder.gen[Banana]
}

sealed trait Fruit
case class GreenBanana(curvature: Double) extends Fruit
case class Apple(poison: Boolean) extends Fruit

object Fruit {
  implicit val decoder: JsonDecoder[Fruit] = DeriveJsonDecoder.gen[Fruit]
  implicit val encoder: JsonEncoder[Fruit] = DeriveJsonEncoder.gen[Fruit]
}
object GreenBanana {
  implicit val decoder: JsonDecoder[GreenBanana] = DeriveJsonDecoder.gen[GreenBanana]
  implicit val encoder: JsonEncoder[GreenBanana] = DeriveJsonEncoder.gen[GreenBanana]
}

object Apple {
  implicit val decoder: JsonDecoder[Apple] = DeriveJsonDecoder.gen[Apple]
  implicit val encoder: JsonEncoder[Apple] = DeriveJsonEncoder.gen[Apple]
}

sealed trait SweetFruit extends Product with Serializable
case class CatarinaBanana(curvature: Double) extends SweetFruit
case class SweetApple(poison: Boolean) extends SweetFruit

object SweetFruit {
  implicit val decoder: JsonDecoder[SweetFruit] = DeriveJsonDecoder.gen[SweetFruit]
  implicit val encoder: JsonEncoder[SweetFruit] = DeriveJsonEncoder.gen[SweetFruit]
}
object CatarinaBanana {
  implicit val decoder: JsonDecoder[CatarinaBanana] = DeriveJsonDecoder.gen[CatarinaBanana]
  implicit val encoder: JsonEncoder[CatarinaBanana] = DeriveJsonEncoder.gen[CatarinaBanana]
}
object SweetApple {
  implicit val decoder: JsonDecoder[SweetApple] = DeriveJsonDecoder.gen[SweetApple]
  implicit val encoder: JsonEncoder[SweetApple] = DeriveJsonEncoder.gen[SweetApple]
}

object ZioJsonApp extends ZIOAppDefault {
  override def run: ZIO[Any, Nothing, ExitCode] =
    program.exitCode

  val program: ZIO[Any, Throwable, Unit] = for {
    _ <- ZIO.succeed(println("Hello ZIO JSON!"))
    banana = Banana(0.5)
    bananaJson = banana.toJson
    _ <- ZIO.succeed(println(bananaJson))

    banana2 = bananaJson.fromJson[Banana]
    _ <- ZIO.succeed(println(banana2))

    greenBanana = GreenBanana(0.5)
    greenBananaJson = greenBanana.toJson
    _ <- ZIO.succeed(println(greenBananaJson))

    greenBanana2 = greenBananaJson.fromJson[GreenBanana]
    _ <- ZIO.succeed(println(greenBanana2))

    apple = Apple(poison = true)
    appleJson = apple.toJson
    _ <- ZIO.succeed(println(appleJson))

    apple2 = appleJson.fromJson[Apple]
    _ <- ZIO.succeed(println(apple2))

    catarinaBanana = CatarinaBanana(0.5)
    catarinaBananaJson = catarinaBanana.toJson
    _ <- ZIO.succeed(println(catarinaBananaJson))

    catarinaBanana2 = catarinaBananaJson.fromJson[CatarinaBanana]
    _ <- ZIO.succeed(println(catarinaBanana2))

    sweetApple = SweetApple(poison = true)
    sweetAppleJson = sweetApple.toJson
    _ <- ZIO.succeed(println(sweetAppleJson))

    sweetApple2 = sweetAppleJson.fromJson[SweetApple]
    _ <- ZIO.succeed(println(sweetApple2))
  } yield ()
}