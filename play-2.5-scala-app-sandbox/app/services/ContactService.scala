package services

import javax.inject._
import scala.collection.mutable.HashMap
import models.Contact
import java.util.concurrent.atomic.AtomicLong

trait IContactService {
  def insert(contact:Contact):Long
  def update(id:Long,contact:Contact):Boolean
  def delete(id:Long):Boolean
  def get(id:Long):Option[Contact]
  def getAll():Option[List[Contact]]
}

@Singleton
class ContactService extends IContactService{
   
  var contacts = new HashMap[Long,Contact]
  var idCounter = new AtomicLong(0)
  
  def insert(contact:Contact):Long = {
     val id = idCounter.incrementAndGet();
     contact.id = Some(id)
     contacts.put(id, contact)
     id
  }
  
  def update(id:Long,contact:Contact):Boolean = {
     val contactDB = contacts.get(id)
     if (contactDB==null) throw new RuntimeException("Could not find contact: " + id)
     contact.id = Some(id)
     contacts.put(id, contact)
     true
  }
  
  def delete(id:Long):Boolean = {
     val contactDB = contacts.get(id)
     if (contactDB==null) throw new RuntimeException("Could not find contact: " + id)
     contacts.remove(id)
     true
  }
  
  def get(id:Long):Option[Contact] = {
     contacts.get(id)  
  }
  
  def getAll():Option[List[Contact]] = {
     if (contacts.values.toList == null || contacts.values.toList.size==0) return None
     Some(contacts.values.toList)
  }
  
}