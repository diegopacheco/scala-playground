package com.github.diegopacheco.scalap99

// P27 (**) Group the elements of a set into disjoint subsets.
//     a) In how many ways can a group of 9 people work in 3 disjoint subgroups
//        of 2, 3 and 4 persons?  Write a function that generates all the
//        possibilities.
//
//        Example:
//        scala> group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
//        res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...
//
//     b) Generalize the above predicate in a way that we can specify a list
//        of group sizes and the predicate will return a list of groups.
//
//        Example:
//        scala> group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
//        res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...
//
//     Note that we do not want permutations of the group members;
//     i.e. ((Aldo, Beat), ...) is the same solution as ((Beat, Aldo), ...).
//     However, we make a difference between ((Aldo, Beat), (Carla, David), ...)
//     and ((Carla, David), (Aldo, Beat), ...).
//
//     You may find more about this combinatorial problem in a good book on
//     discrete mathematics under the term "multinomial coefficients".
object P27:
    def group3[A](ls: List[A]): List[List[List[A]]] =
      for {
        a <- P26.combinations(2, ls)
        noA = ls diff a
        b <- P26.combinations(3, noA)
      } yield List(a, b, noA diff b)

    def group[A](ns: List[Int], ls: List[A]): List[List[List[A]]] = ns match
      case Nil     => List(Nil)
      case n :: ns => P26.combinations(n, ls) flatMap { c =>
        group(ns, ls diff c) map { c :: _ }
      }

object P27Main extends App:
  println(P27.group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")))
  println(P27.group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida")))