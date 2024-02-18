class Stack[A]:
  private var elements: List[A] = Nil
  def size():Int = elements.size
  def push(x: A): Unit =elements = elements.prepended(x)
  def peek: A = elements.head
  def pop(): A =
    val currentTop = peek
    elements = elements.tail
    currentTop

@main def hello(): Unit =
  val stack = Stack[Int]
  stack.push(1)
  stack.push(2)
  stack.push(3)
  println(stack.pop())  // prints 3
  println(stack.pop())  // prints 2
  println(stack.size()) // prints 1