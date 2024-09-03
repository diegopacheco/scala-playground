object Application extends App {

  // baked-in “setters”
  class MutableIntWrapper {
    private var internalValue = 0

    // getter
    def value = internalValue

    // setter
    def value_= (newValue: Int) = {
      internalValue = newValue
    }
  }

  val wrapper = new MutableIntWrapper
  println(wrapper.value) // 0
  wrapper.value = 43 // same as wrapper.value_=(43)
  println(wrapper.value) // 43

}
