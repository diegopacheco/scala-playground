enum Tree[T] derives CanEqual:
    case Branch(left: Tree[T], right: Tree[T]) extends Tree[T]
    case Leaf(elem: T) extends Tree[T]

@main def hello(): Unit =
  import Tree._

  val t:Tree[String] = Tree.Branch(Tree.Leaf("1"),Tree.Leaf("2"))
  println(t)