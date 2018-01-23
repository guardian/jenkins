package com.gu.contentatom.renderer
package renderers
package email

case class CSSSelector(value: String) extends AnyVal
case class CSSProperty(value: String) extends AnyVal
case class CSSValue(value: String) extends AnyVal
case class CSSBlock(decls: List[(CSSProperty, CSSValue)])

case class CSSRuleset(selectors: List[CSSSelector], block: CSSBlock)

object CSS {
  def parse(css: String): List[CSSRuleset] = Nil
}