package com.gu.contentatom.renderer
package renderers
package email
// -----------------------------------------------------------------------------
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
// -----------------------------------------------------------------------------

case class CSSSelector(value: String) extends AnyVal
case class CSSProperty(value: String) extends AnyVal
case class CSSValue(value: String) extends AnyVal
case class CSSBlock(decls: List[(CSSProperty, CSSValue)])

case class CSSRuleset(selectors: List[CSSSelector], block: CSSBlock)

/**
 * A CSS parser loosely based on the [MDN documentation](https://developer.mozilla.org/en-US/docs/Web/CSS/Syntax).
 */
trait CSSRulesetParsers extends StandardTokenParsers {
  def stylesheet: Parser[List[CSSRuleset]] = rep(ruleset)
  
  def ruleset: Parser[CSSRuleset] = selectors ~ block ^^ { 
    case s ~ b => CSSRuleset(s, b) 
  }

  def selectors: Parser[List[CSSSelector]] = 
    rep1sep(selector, ",")

  def block: Parser[CSSBlock] =
    repsep(decl, ";") <~ opt(";") ^^ { CSSBlock(_) }

  def decl: Parser[(CSSProperty, CSSValue)] =
    property ~ (":" ~> value) ^^ { 
      case p ~ v => (p, v) 
    }

  def selector: Parser[CSSSelector] = 
    basicSelector ~ 
      opt(pseudoClass) ~ 
      opt(pseudoElement) ~ 
      opt(adjacentSiblingSelector | generalSiblingSelector | childSelector | descendantSelector) ^^ {
        case a ~ None    ~ None    ~ None    => CSSSelector(a)
        case a ~ Some(b) ~ None    ~ None    => CSSSelector(a + b)
        case a ~ Some(b) ~ Some(c) ~ None    => CSSSelector(a + b + c)
        case a ~ Some(b) ~ Some(c) ~ Some(d) => CSSSelector(a + b + c + "," + d)
      }
    
  def basicSelector: Parser[String] = classSelector | idSelector | universalSelector | attributeSelector

  def adjacentSiblingSelector: Parser[String] = "+" ~> selector ^^ { "+" + _ }

  def generalSiblingSelector: Parser[String] = "~" ~> selector ^^ { "~" + _ }

  def childSelector: Parser[String] = ">" ~> selector ^^ { ">" + _ }

  def descendantSelector: Parser[String] = rep1(" ") ~> selector ^^ { " " + _.value }

  def classSelector: Parser[String] = "." ~> ident ^^ { "." + _ }

  def idSelector: Parser[String] = "#" ~> ident ^^ { "#" + _ }

  def universalSelector: Parser[String] = "*"

  def attributeSelector: Parser[String] = "[" ~> rep1(not("]")) <~ "]" ^^ { "[" + _ + "]" }

  def pseudoClass: Parser[String] =
    ":active" | 
    ":any" | 
    ":any-link" | 
    ":checked" | 
    ":default" | 
    ":dir(rtl)" | 
    ":dir(ltr)" | 
    ":disabled" | 
    ":empty" | 
    ":enabled" | 
    ":first" | 
    ":first-child" | 
    ":first-of-type" | 
    ":fullscreen" | 
    ":focus" | 
    ":hover" | 
    ":indeterminate" | 
    ":in-range" | 
    ":invalid" | 
    (":lang(" ~> rep1(ident | "-") <~ ")" ^^ collapse(":lang(", ")")) | 
    ":last-child" | 
    ":last-of-type" | 
    ":left" | 
    ":link" | 
    (":not(" ~> selector <~ ")" ^^ { ":not(" + _ + ")" }) | 
    (":nth-child(" ~> rep1(ident | "+") <~ ")" ^^ collapse(":nth-child(", ")")) | 
    (":nth-last-child(" ~> rep1(ident | "+") <~ ")" ^^ collapse(":nth-last-child(", ")")) | 
    (":nth-last-of-type(" ~> rep1(ident | "+") <~ ")" ^^ collapse(":nth-last-of-type(", ")")) | 
    (":nth-of-type(" ~> rep1(ident | "+") <~ ")" ^^ collapse(":nth-of-type(", ")")) | 
    ":only-child" | 
    ":only-of-type" | 
    ":optional" | 
    ":out-of-range" | 
    ":read-only" | 
    ":read-write" | 
    ":required" | 
    ":right" | 
    ":root" | 
    ":scope" | 
    ":target" | 
    ":valid" | 
    ":visited"

  def pseudoElement: Parser[String] =
    "::before" | "::after" | "::first-letter" | "::first-line" | "::selection"

  def property: Parser[CSSProperty] = ident ^^ { CSSProperty(_) }
  
  def value: Parser[CSSValue] = rep1(not(";" | "}")) ^^ { chrs => CSSValue(chrs.mkString) }

  private def collapse(prefix: String, postfix: String): List[Any] => String = chrs => prefix + chrs.mkString + postfix
}

object CSS {
  val parser = new CSSRulesetParsers

  def parse(css: String): List[CSSRuleset] = parser.parseAll(parser.stylesheet, css) match {
    case parser.Success(res, _) => res
    case _                      => Nil     
  }
}