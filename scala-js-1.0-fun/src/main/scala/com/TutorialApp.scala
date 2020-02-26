package com

import org.scalajs.dom
import org.scalajs.dom.document

object TutorialApp {

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  def main(args: Array[String]): Unit = {
   appendPar(document.body, "Hello World")
  }

}