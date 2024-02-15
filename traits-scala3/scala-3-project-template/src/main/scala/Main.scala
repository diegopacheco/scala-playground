@main def hello(): Unit =
  val max = new TRex("Max")
  println(max.speak())
  println(max.noise())

trait Animal
trait TailAnimal extends Animal
trait Dino extends TailAnimal:
  def speak(): String = "roar"

trait Toy extends Animal:
  def noise(): String = "fluflyuiiiiii "

class TRex(name: String) extends Dino with Toy:
  override def speak(): String = "Roarrrrrrrrrrr! Rawrrrrrrrrr! "