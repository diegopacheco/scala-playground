enum Color:
  case Organge, Purple, Gray

enum MoreColors:
  case Black  extends MoreColors
  case White  extends MoreColors
  case Brown  extends MoreColors

enum BasicColor(val rgb: Int):
  case Red   extends BasicColor(0xFF0000)
  case Green extends BasicColor(0x00FF00)
  case Blue  extends BasicColor(0x0000FF)

enum Planet(mass: Double, radius: Double):
  private final val G = 6.67300E-11
  def surfaceGravity = G * mass / (radius * radius)
  def surfaceWeight(otherMass: Double) =  otherMass * surfaceGravity

  case Mercury extends Planet(3.303e+23, 2.4397e6)
  case Venus   extends Planet(4.869e+24, 6.0518e6)
  case Earth   extends Planet(5.976e+24, 6.37814e6)

@main def hello(): Unit =
  println(BasicColor.Green.rgb)
  println(Planet.Mercury.surfaceGravity)
