package com.gu.contentatom.renderer
package renderers
package email
// -----------------------------------------------------------------------------
import scala.util.parsing.combinator.RegexParsers
// -----------------------------------------------------------------------------

case class CSSSelector(value: String) extends AnyVal
case class CSSProperty(value: String) extends AnyVal
case class CSSValue(value: String) extends AnyVal
case class CSSBlock(decls: List[(CSSProperty, CSSValue)])

case class CSSRuleset(selectors: List[CSSSelector], block: CSSBlock)

/**
 * A CSS parser loosely based on the [MDN documentation](https://developer.mozilla.org/en-US/docs/Web/CSS/Syntax).
 */
class CSSRulesetParsers extends RegexParsers {
  // Terminals
  def property = """[\w\d-]+""".r

  def value = """[^;}]+""".r

  def typeSelector = """\w[\w-]+""".r

  def ident = """[\w\d_-]+""".r

  // Public API
  def parseCss(css: String): List[CSSRuleset] =
    parseAll(stylesheet, css).getOrElse(Nil)

  // Generation rules
  def stylesheet: Parser[List[CSSRuleset]] = ruleset.*
  
  def ruleset: Parser[CSSRuleset] = selectors ~ block ^^ { 
    case s ~ b => CSSRuleset(s, b) 
  }

  def selectors: Parser[List[CSSSelector]] = 
    rep1sep(selector, ",")

  def block: Parser[CSSBlock] =
    "{" ~> (repsep(decl, ";") <~ ";".?) <~ "}" ^^ { CSSBlock(_) }

  def decl: Parser[(CSSProperty, CSSValue)] =
    property ~ (":" ~> value) ^^ { 
      case p ~ v => (CSSProperty(p), CSSValue(v)) 
    }

  def selector: Parser[CSSSelector] = 
    (rep1(basicSelector) ^^ { _.mkString }) ~
      pseudoClass.* ~
      pseudoElement.? ~
      (adjacentSiblingSelector | generalSiblingSelector | childSelector | (selector ^^ { " " + _.value })).* ^^ { 
        case a ~ Nil ~ None    ~ Nil  => CSSSelector(a)
        case a ~ Nil ~ None    ~ ds   => CSSSelector(a + ds.mkString)
        case a ~ Nil ~ Some(c) ~ Nil  => CSSSelector(a + c)
        case a ~ Nil ~ Some(c) ~ ds   => CSSSelector(a + c + ds.mkString)
        case a ~ bs  ~ None    ~ Nil  => CSSSelector(a + bs.mkString)
        case a ~ bs  ~ None    ~ ds   => CSSSelector(a + bs.mkString + ds.mkString)
        case a ~ bs  ~ Some(c) ~ Nil  => CSSSelector(a + bs.mkString + c)
        case a ~ bs  ~ Some(c) ~ ds   => CSSSelector(a + bs.mkString + c + ds.mkString)
      }
    
  def basicSelector: Parser[String] = 
    typeSelector | classSelector | idSelector | universalSelector | attributeSelector

  def adjacentSiblingSelector: Parser[String] = "+" ~> selector ^^ { "+" + _.value }

  def generalSiblingSelector: Parser[String] = "~" ~> selector ^^ { "~" + _.value }

  def childSelector: Parser[String] = ">" ~> selector ^^ { ">" + _.value }

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

  private def collapse(prefix: String, postfix: String): List[Any] => String = chrs => prefix + chrs.mkString + postfix
}

object CSS extends CSSRulesetParsers {}