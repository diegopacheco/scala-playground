package models

case class Contact(firstname: String,
                   lastname: String,
                   company: Option[String])

object Contact {
  def save(contact: Contact): Int = 99
}
