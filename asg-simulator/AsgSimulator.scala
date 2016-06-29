object AsgSimulator extends App {
    
   import java.text.DecimalFormat

   def simulate(users:Int, boxes:Int, fx:Double,up:Double,down:Double):Double = {
      val diff = users / (boxes * fx) 
      val action = shouldScale(diff,up,down)
      val capacity = boxes * fx

      val formatedUsers    = users.toString.padTo(6, ' ')
      val formatedBoxes    = boxes.toString.padTo(3, ' ')
      val formatedDiff     = new DecimalFormat("#.##").format(diff).padTo(4, ' ')
      val formatedAction   = action.padTo(5, ' ')
      val formatedCapacity = capacity.toInt.toString.padTo(6, ' ')
      println(s"Live Users: $formatedUsers Boxes: $formatedBoxes Capacity: $formatedCapacity Diff: $formatedDiff  Will: $formatedAction  ")
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

   simulate(1000 , 1,  fx,up,down)
   simulate(1800 , 1,  fx,up,down)
   simulate(1900 , 1,  fx,up,down)   
   simulate(2000 , 1,  fx,up,down)
   simulate(3000 , 1,  fx,up,down)
   simulate(4000 , 1,  fx,up,down)   
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
   simulate(8000 , 1,  fx,up,down)
   simulate(1000 , 10, fx,up,down)

}


