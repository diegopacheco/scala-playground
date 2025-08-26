sealed trait DoorState
case object Closed extends DoorState  
case object Open extends DoorState
case object Locked extends DoorState

class Door[S <: DoorState] private (state: S):
  def open(using S =:= Closed.type): Door[Open.type] = Door(Open)
  def close(using S =:= Open.type): Door[Closed.type] = Door(Closed)
  def lock(using S =:= Closed.type): Door[Locked.type] = Door(Locked)
  def unlock(using S =:= Locked.type): Door[Closed.type] = Door(Closed)

object Door:
  def closed: Door[Closed.type] = Door(Closed)

@main def main():Unit =
  val door = Door.closed.open.close.lock.unlock.open
  println(door)
