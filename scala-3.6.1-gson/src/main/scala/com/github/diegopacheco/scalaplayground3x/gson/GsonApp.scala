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

class ListTypeAdapter extends TypeAdapter[List[Option[Int]]] {
  override def write(out: JsonWriter, value: List[Option[Int]]): Unit = {
    out.beginArray()
    value.foreach {
      case Some(v) => out.value(v)
      case None => out.nullValue()
    }
    out.endArray()
  }

  override def read(in: JsonReader): List[Option[Int]] = {
    val list = scala.collection.mutable.ListBuffer[Option[Int]]()
    in.beginArray()
    while (in.hasNext) {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull()
        list += None
      } else {
        list += Some(in.nextInt())
      }
    }
    in.endArray()
    list.toList
  }
}

object GsonApp extends App {
  val gsonBuilder = new GsonBuilder()
  val listType: Type = new TypeToken[List[Option[Int]]]() {}.getType
  gsonBuilder.registerTypeAdapter(listType, new ListTypeAdapter())
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
}