package com.github.diegopacheco.scala3.playground.features

@main def EnumParametersApp():Unit = {

  enum Color(val rgb:Int):
    case Red   extends Color(0xFF0000)
    case Green extends Color(0x00FF00)
    case Blue  extends Color(0x0000FF)
    
  println(Color.Green.rgb) // prints 65280

}
