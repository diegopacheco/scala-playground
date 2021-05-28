import org.apache.commons.lang3.ArrayUtils

object SimpleApp extends App{
  val array1 = Array(1, 2, 3)
  val array2 = Array(1, 2, 3)
  print("Arrays have the same len? " + ArrayUtils.isSameLength(array1, array2) )
}