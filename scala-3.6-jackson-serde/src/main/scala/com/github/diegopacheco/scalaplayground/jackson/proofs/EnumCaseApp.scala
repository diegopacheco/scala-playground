package com.github.diegopacheco.scalaplayground.jackson.proofs

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer, JsonNode, JsonSerializer, SerializerProvider}
import com.github.diegopacheco.scalaplayground.jackson.serde.SerializationService.{fromJson, toJson}

object PizzaModule {
  private class PizzaStyleSerializer extends JsonSerializer[PizzaStyle] {
    override def serialize(value: PizzaStyle, gen: JsonGenerator, serializers: SerializerProvider): Unit = {
      gen.writeStartObject()
      gen.writeStringField("pizzaStyle", value.toString)
      gen.writeEndObject()
    }
  }

  private class PizzaStyleDeserializer extends JsonDeserializer[PizzaStyle] {
    override def deserialize(p: JsonParser, context: DeserializationContext): PizzaStyle = {
      val node = p.getCodec.readTree[JsonNode](p)
      PizzaStyle.valueOf(node.get("pizzaStyle").asText())
    }
  }

  class CustomPizzaModule extends SimpleModule {
    addSerializer(classOf[PizzaStyle], new PizzaStyleSerializer)
    addDeserializer(classOf[PizzaStyle], new PizzaStyleDeserializer)
  }
}

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum PizzaStyle {
  case NY, Chicago, California
}

object EnumCaseApp extends App{

  private val ny = PizzaStyle.NY
  val json = toJson(ny)
  println(json)

  private val ny2 = fromJson(json,classOf[PizzaStyle])
  println(ny2)
  println(ny == ny2)

}
