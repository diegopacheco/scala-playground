import scala.annotation.experimental

@experimental
object NamedTuplesMain extends App{

    import scala.language.experimental.namedTuples

    type Point = (x: Int, y: Int)
    val point: Point = (x = 1, y = 2)
    println(point)

    val is_it_point = (x = 5, y = 3)
    val it_is: Point = is_it_point
    println(it_is.x)
    println(it_is.y)

}
