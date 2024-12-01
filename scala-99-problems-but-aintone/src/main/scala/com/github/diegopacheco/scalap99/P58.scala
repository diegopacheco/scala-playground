package com.github.diegopacheco.scalap99

// P58 (**) Generate-and-test paradigm.
//     Apply the generate-and-test paradigm to construct all symmetric,
//     completely balanced binary trees with a given number of nodes.
//
//     scala> Tree.symmetricBalancedTrees(5, "x")
//     res0: List[Node[String]] = List(T(x T(x . T(x . .)) T(x T(x . .) .)), T(x T(x T(x . .) .) T(x . T(x . .))))

import com.github.diegopacheco.scalap99.P57.{Node, Tree, End}

object P58 {
  def cBalanced[T](n: Int, v: T): List[Tree[T]] =
    if (n < 1) List(End)
    else if (n % 2 == 1) {
      val subtrees = cBalanced(n / 2, v)
      for {
        l <- subtrees
        r <- subtrees
      } yield Node(v, l, r)
    } else {
      val subtrees1 = cBalanced((n - 1) / 2, v)
      val subtrees2 = cBalanced((n - 1) / 2 + 1, v)
      (for {
        l <- subtrees1
        r <- subtrees2
      } yield Node(v, l, r)) :::
        (for {
          l <- subtrees2
          r <- subtrees1
        } yield Node(v, l, r))
    }

  def symmetricBalancedTrees[T](n: Int, value: T): List[Node[T]] = {
    cBalanced(n, value).collect {
      case node: Node[T] => node
    }.filter(_.isSymmetric)
  }
}

object P58Main extends App {
  println(P58.symmetricBalancedTrees(5, "x"))
}