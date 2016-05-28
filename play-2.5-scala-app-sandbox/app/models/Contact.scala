package models

case class Contact(var firstname: String,
                   var  lastname: String,
                   var  company: Option[String],
                   var  id:Option[Long] = Some(-1))

object Contact {
  def save(contact: Contact): Int = 99
}
