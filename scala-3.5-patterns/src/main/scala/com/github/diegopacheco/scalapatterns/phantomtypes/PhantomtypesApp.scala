package com.github.diegopacheco.scalapatterns.phantomtypes

sealed trait Draft
sealed trait UnderReview
sealed trait Approved
case class Document[S](content: String)

object Document {
  def create(content: String): Document[Draft] = Document[Draft](content)
  def submitForReview(doc: Document[Draft]): Document[UnderReview] = Document[UnderReview](doc.content)
  def approve(doc: Document[UnderReview]): Document[Approved] = Document[Approved](doc.content + " [Approved]")
}

object PhantomtypesApp extends App{
  val draftDoc = Document.create("This is a document.")
  val reviewDoc = Document.submitForReview(draftDoc)
  val approvedDoc = Document.approve(reviewDoc)

  // The following line would cause a compile-time error:
  // val invalidTransition = Document.approve(draftDoc)
  println(s"Draft Document: ${draftDoc.content}")
  println(s"Under Review Document: ${reviewDoc.content}")
  println(s"Approved Document: ${approvedDoc.content}")
}
