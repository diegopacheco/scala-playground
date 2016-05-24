package controllers

import javax.inject._
import play.api._
import play.api.mvc._

case class Customer(name:String)
case class Item(id:String,description:String,numberOfItens:Int)
case class Order(itens:List[Item])
case class CustomerOrder(customer:Customer,order:Order)

@Singleton
class TwilController @Inject() extends Controller {
      
    def list = Action {
         val customer = Customer("Diego")
         val order = Order(List(Item("1","Razor Sc2 Mechanial Keybaord",1),Item("1","Razor Sc2 Mouse",2))) 
         Ok(views.html.customer_order(CustomerOrder(customer,order)))
    }
    
    def home = Action {
      Ok(views.html.customer())
    }
    
    def tags = Action {
      Ok(views.html.customer_tags())
    }
  
}