package com.github.diegopacheco.scala.idiomatic.monads

/**
 * 
 * This is a Functional programing concept which is strong in Haskell and Scala.
 * This is a Monad - A monad is just a wrapper for some type. There are several other monads in the language like Try, Option and Future.
 * A Nomand need to implement 2 methods: apply(also know as pure in scala and return in haskell) and flatMap(also know as bind(>>=) in haskell). 
 * Monads have laws and there are 3 laws: Left identity, Right identity and Associativity.
 * 
 * This trait just descrive a generic monad signature, in otherwords is like a base wrapper telling how other wrappers should wrap. 
 * 
 * @author diegopacheco
 * @version 1.0
 * 
 */
trait Monad[A] {
  def apply[A](v:A):Monad[A] 
  def flatMap[B](f: A => Monad[B]):Monad[B]
}

/**
 * Here we have a 3 real monad called Number, Even and Odd. Basically as you grasp here this monads are being wrapped by a companion object called NumberMonad.
 * The idea here is simple, Number represents and Int number. Even represents and Int even number and Odd a Int odd number. 
 * NumberMonad.apply decides if the number should be Even or Odd.  
 */
object NumberMonad {
  
  class Number[A](v:A) {
      def apply[A](v:A):Number[A] = new Number(v)
      def flatMap[B](f: A => Number[B]):Number[B] = f(v)    
  }
  
  case class Even(v:Int) extends Number[Int](v)
  case class Odd(v:Int)  extends Number[Int](v)
  
  def apply(v:Int):Number[Int] = if (v%2==0) Even(v) else Odd(v)
}

/**
 * Here we see how to use Nomads in practice, so we call apply and we call flatMap in order to transform our monads.
 */
object MonadMain extends App {
  
  import NumberMonad._
  
  val nomad1 = NumberMonad(2)
  println(nomad1.getClass.getSimpleName)
  
  val nomad2 = NumberMonad(5)
  println(nomad2.getClass.getSimpleName)
  
  def inc2(v:Int):Even = Even(v+2)
  val nomad3 = nomad1.flatMap(inc2)
  println(nomad3)
  
}