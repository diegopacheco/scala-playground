@main def main():Unit =
  enum State:
    case Idle, Running, Paused, Stopped
  
  enum Event:
    case Start, Pause, Resume, Stop
  
  def transition(state: State, event: Event): State = (state, event) match
    case (State.Idle, Event.Start) => State.Running
    case (State.Running, Event.Pause) => State.Paused
    case (State.Paused, Event.Resume) => State.Running
    case (State.Running | State.Paused, Event.Stop) => State.Stopped
    case _ => state

  println(transition(State.Idle, Event.Start))

