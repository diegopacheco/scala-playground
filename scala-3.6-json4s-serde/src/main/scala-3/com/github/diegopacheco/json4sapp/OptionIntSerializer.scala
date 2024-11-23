package com.github.diegopacheco.json4sapp

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JInt, JNull}

object OptionIntSerializer extends CustomSerializer[Option[Int]](format => (
  {
    case JInt(x) => Some(x.toInt)
    case JNull => None
  },
  {
    case Some(x: Int) => JInt(x)
    case None => JNull
  }
))
