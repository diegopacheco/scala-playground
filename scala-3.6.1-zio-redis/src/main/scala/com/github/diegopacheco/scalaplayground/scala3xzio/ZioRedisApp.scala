package com.github.diegopacheco.scalaplayground.scala3xzio

import zio._
import zio.redis._
import zio.schema._
import zio.schema.codec._

object ZioRedisApp extends ZIOAppDefault {

  object ProtobufCodecSupplier extends CodecSupplier {
    def get[A: Schema]: BinaryCodec[A] = ProtobufCodec.protobufCodec
  }

  val myApp: ZIO[Redis, RedisError, Unit] =
    for {
      redis <- ZIO.service[Redis]
      _ <- redis.set("myKey", 8L, Some(1.minutes))
      v <- redis.get("myKey").returning[Long]
      _ <- Console.printLine(s"Value of myKey: $v").orDie
      _ <- redis.hSet("myHash", ("k1", 6), ("k2", 2))
      _ <- redis.rPush("myList", 1, 2, 3, 4)
      _ <- redis.sAdd("mySet", "a", "b", "a", "c")
    } yield ()

  override def run =
    myApp.provide(Redis.local, ZLayer.succeed[CodecSupplier](ProtobufCodecSupplier))
}