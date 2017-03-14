package example

object Main {
  def main(args: Array[String]): Unit = {
	
	import scalanative.native._

        // CString is an alias to Ptr[CChar]
	val msg: CString = c"Hello, world!"
	stdio.printf(msg)
  }
}
