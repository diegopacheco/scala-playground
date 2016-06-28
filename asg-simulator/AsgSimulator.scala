object AsgSimulator extends App {
 
   def simulate(users:Int, boxes:Int, fx:Double,up:Double,down:Double):Double = {
      val diff = users / (boxes * fx) 
      val action = shouldScale(diff,up,down)
      val capacity = boxes * fx
      println(s"Users: $users Boxes: $boxes Result: $diff Will: $action Capacity: $capacity ")
      diff
   }

   def shouldScale(diff:Double,up:Double,down:Double):String= {
      if (diff >= up) return "UP"
      else if (diff <= down) return "DOWN"
      else return "KEEP"
   }
   
   val fx   = 2000.0
   val up   = 0.8
   val down = 0.6
   println(s"Simulation Thresholds (Up: $up Down: $down - Fx: $fx) formula f(Scale)=Users/(boxes*Fx): ")  

   simulate(2000 , 1,  fx,up,down)
   simulate(3000 , 1,  fx,up,down)
   simulate(3000 , 2,  fx,up,down)
   simulate(4000 , 2,  fx,up,down)
   simulate(5000 , 2,  fx,up,down)
   simulate(5000 , 3,  fx,up,down)
   simulate(5000 , 4,  fx,up,down)
   simulate(5000 , 5,  fx,up,down)
   simulate(5000 , 6,  fx,up,down)
   simulate(6000 , 6,  fx,up,down)
   simulate(10000, 5,  fx,up,down)
   simulate(20000, 10, fx,up,down)
   simulate(200  , 10, fx,up,down)

}


