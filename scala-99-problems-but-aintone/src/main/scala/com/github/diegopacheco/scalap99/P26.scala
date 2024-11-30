package com.github.diegopacheco.scalap99

// P26 (**) Generate the combinations of K distinct objects chosen from the N
//          elements of a list.
//     In how many ways can a committee of 3 be chosen from a group of 12
//     people?  We all know that there are C(12,3) = 220 possibilities (C(N,K)
//     denotes the well-known binomial coefficient).  For pure mathematicians,
//     this result may be great.  But we want to really generate all the possibilities.
//
//     Example:
//     scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
//     res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
object P26:
  def combinations[T](n: Int, l: List[T]): List[List[T]] =
    def combR(n: Int, l: List[T], r: List[T]): List[List[T]] = (n, l) match
      case (0, _) => List(r)
      case (_, Nil) => Nil
      case (n, h :: tail) => combR(n - 1, tail, h :: r).map(h :: _) ::: combR(n, tail, r)
    combR(n, l, Nil)

  def combinations2[T](n: Int, l: List[T]): List[List[T]] =
    if n == 0 then List(Nil)
    else l match
      case Nil => Nil
      case h :: tail => combinations2(n - 1, tail).map(h :: _) ::: combinations2(n, tail)

object P26Main extends App:
  println(P26.combinations(3, List('a', 'b', 'c', 'd', 'e', 'f')))
  println(P26.combinations2(3, List('a', 'b', 'c', 'd', 'e', 'f')))