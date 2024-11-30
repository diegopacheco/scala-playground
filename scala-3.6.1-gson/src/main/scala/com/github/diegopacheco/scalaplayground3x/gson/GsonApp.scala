package com.github.diegopacheco.scalaplayground3x.gson

import com.google.gson.reflect.TypeToken
import com.google.gson.stream.{JsonReader, JsonToken, JsonWriter}
import com.google.gson.{Gson, GsonBuilder, TypeAdapter}
import java.lang.reflect.Type

case class Person(name: String, age: Int)

trait Animal
case class Dog(name: String) extends Animal

abstract class Fish
case class Shark(name: String) extends Fish

case class MyType(x: String, y: List[Option[Int]])

class OptionTypeAdapter[T](implicit tt: TypeToken[T]) extends TypeAdapter[Option[T]] {
  override def write(out: JsonWriter, value: Option[T]): Unit = {
    value match {
      case Some(v) => new Gson().toJson(v, tt.getType, out)
      case None => out.nullValue()
    }
  }
  override def read(in: JsonReader): Option[T] = {
    if (in.peek() == JsonToken.NULL) {
      in.nextNull()
      None
    } else {
      Some(new Gson().fromJson(in, tt.getType))
    }
  }
}

class ListOptionTypeAdapter[T](implicit tt: TypeToken[T]) extends TypeAdapter[List[Option[T]]] {
  override def write(out: JsonWriter, value: List[Option[T]]): Unit = {
    out.beginArray()
    value.foreach {
      case Some(v) => new Gson().toJson(v, tt.getType, out)
      case None => out.nullValue()
    }
    out.endArray()
  }

  override def read(in: JsonReader): List[Option[T]] = {
    val list = scala.collection.mutable.ListBuffer[Option[T]]()
    in.beginArray()
    while (in.hasNext) {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull()
        list += None
      } else {
        list += Some(new Gson().fromJson(in, tt.getType))
      }
    }
    in.endArray()
    list.toList
  }
}

object GsonApp extends App {
  val gsonBuilder = new GsonBuilder()

  val optionType: Type = new TypeToken[Option[Int]]() {}.getType
  gsonBuilder.registerTypeAdapter(optionType, new OptionTypeAdapter[Int]()(TypeToken.get(classOf[Int])))

  val listOptionType: Type = new TypeToken[List[Option[Int]]]() {}.getType
  gsonBuilder.registerTypeAdapter(listOptionType, new ListOptionTypeAdapter[Int]()(TypeToken.get(classOf[Int])))

  val gson = gsonBuilder.create()

  val jhonDoe = Person("Jhon Doe", 30)
  println(gson.toJson(jhonDoe))

  val back1 = gson.fromJson(gson.toJson(jhonDoe), classOf[Person])
  println(back1)

  val dog = Dog("Rex")
  println(gson.toJson(dog))

  val back2 = gson.fromJson(gson.toJson(dog), classOf[Dog])
  println(back2)

  val shark = Shark("Jaws")
  println(gson.toJson(shark))

  val back3 = gson.fromJson(gson.toJson(shark), classOf[Shark])
  println(back3)

  val myObject = MyType("example", List(Some(1), None, Some(2)))
  println(gson.toJson(myObject))

  val back4 = gson.fromJson(gson.toJson(myObject), classOf[MyType])
  println(back4)

  val op1 = Option(1)
  println(gson.toJson(op1))

  val backOpt = gson.fromJson(gson.toJson(op1), classOf[Option[Int]])
  println(backOpt)
}