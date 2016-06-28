object AsgSimulator extends App {
 
   def simulate(users:Int, boxes:Int, fx:Double,up:Double,down:Double):Double = {
      val current = users / (boxes * fx) 
      val action = shouldScale(current,up,down)
      println(s"f(Scale)=Users/(boxes*Fx) Users: $users Boxes: $boxes Fx: $fx Result: $current Diff: ${boxes-current} Will: $action Up: $up Down: $down")
      current
   }

   def shouldScale(current:Double,up:Double,down:Double):String= {
      if (current >= up) return "UP"
      else if (current <= down) return "DOWN"
      else return "KEEP"
   }
   
   
   simulate(2000 , 1,  2000.0,0.8,0.6)
   simulate(3000 , 1,  2000.0,0.8,0.6)
   simulate(3000 , 2,  2000.0,0.8,0.6)
   simulate(4000 , 2,  2000.0,0.8,0.6)
   simulate(5000 , 2,  2000.0,0.8,0.6)
   simulate(5000 , 3,  2000.0,0.8,0.6)
   simulate(5000 , 4,  2000.0,0.8,0.6)
   simulate(5000 , 5,  2000.0,0.8,0.6)
   simulate(5000 , 6,  2000.0,0.8,0.6)
   simulate(6000 , 6,  2000.0,0.8,0.6)
   simulate(10000, 5,  2000.0,0.8,0.6)
   simulate(20000, 10, 2000.0,0.8,0.6)
   simulate(200  , 10, 2000.0,0.8,0.6)
}


