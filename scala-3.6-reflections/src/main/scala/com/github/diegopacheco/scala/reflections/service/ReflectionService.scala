package com.github.diegopacheco.scala.reflections.service

object ReflectionService {

  def listMethods[T](clazz:Class[T]):List[String] = {
    clazz.getDeclaredMethods.map(_.getName).toList
  }

  def setValue[T,V](clazz:Class[T],instance:T,fieldName:String,value:V):Unit = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.set(instance,value)
  }

  def getValue[T,V](clazz:Class[T],instance:T,fieldName:String):V = {
    val field = clazz.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.get(instance).asInstanceOf[V]
  }

}
