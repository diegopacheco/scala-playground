/* Does not preserve ordering */
def removeDuplicates[A](xsOuter: List[A]): List[A] = {
  @annotation.tailrec
  def removeDuplicatesInner(xs: List[A], collected: List[A]): List[A] = xs match {
    case Nil => collected
    case x :: ys => if (collected.contains(x)) removeDuplicatesInner(ys, collected) else removeDuplicatesInner(ys, x :: collected)
  }

  removeDuplicatesInner(xsOuter, Nil)
}

/* Preserve order */
def removeDuplicatesV2[A](list: List[A]): List[A] =
  list.foldLeft(List.empty[A]) { (partialResult, element) =>
    if (partialResult.contains(element)) partialResult
    else partialResult :+ element
}

def msg = "Removing duplicates"
def arg = List("a", "a", "a", "b", "a", 1, 2, 3, 4, 1, 1, 1, 5)

@main def hello: Unit =
  println(msg)
  println(arg)
  println(removeDuplicates(arg))
  println(removeDuplicatesV2(arg))