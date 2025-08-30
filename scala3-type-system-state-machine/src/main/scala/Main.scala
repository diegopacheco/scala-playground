sealed trait State {
  def name: String
}
trait Locked extends State {
  def name: String = "Locked"
}
trait Unlocked extends State {
  def name: String = "Unlocked"
}

case class Door[S <: State]()(using evidence: S) {
  override def toString: String = s"Door(${evidence.name})"
}

@main def main():Unit =
  given Locked = new Locked {}
  given Unlocked = new Unlocked {}
  
  def unlock(door: Door[Locked]): Door[Unlocked] = Door()
  def lock(door: Door[Unlocked]): Door[Locked] = Door()

  val door = Door[Locked]()
  val unlockedDoor = unlock(door)
  val lockedDoor = lock(unlockedDoor)
  println(lockedDoor)
  println(unlockedDoor)
