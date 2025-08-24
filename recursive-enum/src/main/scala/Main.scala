@main def main():Unit =
  
  enum Tree[+T]:
    case Leaf(value: T)
    case Branch(left: Tree[T], right: Tree[T])
  
    def size: Int = this match
      case Leaf(_) => 1
      case Branch(l, r) => 1 + l.size + r.size

  val tree = Tree.Branch(Tree.Leaf(1), Tree.Branch(Tree.Leaf(2), Tree.Leaf(3)))
  println(tree.size)
