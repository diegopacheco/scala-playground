import org.typelevel.literally.Literally

case class Port private (value: Int)

object Port {
  val MinValue = 0
  val MaxValue = 65535

  def fromInt(i: Int): Option[Port] =
    if (i < MinValue || i > MaxValue) None else Some(new Port(i))
}

object literals:
  extension (inline ctx: StringContext)
    inline def port(inline args: Any*): Port =
      ${PortLiteral('ctx, 'args)}

  object PortLiteral extends Literally[Port]:
    def validate(s: String)(using Quotes) =
      s.toIntOption.flatMap(Port.fromInt) match
        case None => Left(s"invalid port - must be integer between ${Port.MinValue} and ${Port.MaxValue}")
        case Some(_) => Right('{Port.fromInt(${Expr(s)}.toInt).get})