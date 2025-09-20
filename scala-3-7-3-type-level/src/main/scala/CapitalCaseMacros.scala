import scala.quoted.*

//
// It's a Macro and must be in a separate file.
//
object CapitalCaseMacros:
  transparent inline def capitalCase[S <: String] = ${ capitalCaseImpl[S] }

  private def capitalCaseImpl[S <: String](using Quotes, Type[S]): Expr[String] =
    import quotes.reflect.*
    TypeRepr.of[S] match
      case ConstantType(StringConstant(s)) =>
        val out = if s.isEmpty then s else s.head.toUpper + s.tail
        Expr(out) // literal -> singleton type "Hello"
      case _ =>
        report.error("capitalCase requires a known string literal type (e.g., capitalCase[\"hello\"])")
        Expr("")