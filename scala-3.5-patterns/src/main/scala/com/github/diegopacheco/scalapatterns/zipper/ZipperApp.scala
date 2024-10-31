package com.github.diegopacheco.scalapatterns.zipper

case class Zipper[A](left: List[A], focus: A, right: List[A]) {
  def moveLeft: Option[Zipper[A]] = left match {
    case Nil => None
    case h :: t => Some(Zipper(t, h, focus :: right))
  }

  def moveRight: Option[Zipper[A]] = right match {
    case Nil => None
    case h :: t => Some(Zipper(focus :: left, h, t))
  }

  def update(newFocus: A): Zipper[A] = Zipper(left, newFocus, right)
  def toList: List[A] = left.reverse ++ (focus :: right)
}

object Zipper {
  def fromList[A](list: List[A]): Option[Zipper[A]] = list match {
    case Nil => None
    case h :: t => Some(Zipper(Nil, h, t))
  }
}

object ZipperApp extends App{
  val list = List(1, 2, 3, 4, 5)
  val zipper = Zipper.fromList(list).get

  val movedRight = zipper.moveRight.get
  println(movedRight.focus) // Output: 2

  val updated = movedRight.update(10)
  println(updated.toList) // Output: List(1, 10, 3, 4, 5)

  val movedLeft = updated.moveLeft.get
  println(movedLeft.focus) // Output: 1
}
