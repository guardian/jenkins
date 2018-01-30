package com.gu.contentatom.renderer
package renderers
package email

import org.jsoup.Jsoup
import org.jsoup.nodes.{ Document, Element }
import org.jsoup.safety.Whitelist

import collection.JavaConverters._

object Hydrator {
  def whitelist = Whitelist.basicWithImages()
    .addTags("table", "tr", "td")
    .addAttributes("table", "align", "border", "cellpadding", "cellspacing")
    .addAttributes(":all", "style", "class", "width", "height")

  def document: String => Document = 
    doc => Jsoup.parse(Jsoup.clean(doc, whitelist))

  def stylesheet: String => List[CSSRuleset] =
    CSS.parseCss(_)
 
  def query(el: Element, sel: CSSSelector): List[Element] = 
    el.select(sel.value).asScala.toList
  
  def update(el: Element, prop: CSSProperty, value: CSSValue): Element =
    el.attr("style", s"""${prop.value}:${value.value};${el.attr("style")}""")

  def run(doc: Document, rules: CSSRuleset): Document = {
    for {
      selector <- rules.selectors
      element <- query(doc, selector)
      (prop, value) <- rules.block.decls
    } {
      update(element, prop, value)
    }
    doc
  }

  def apply(html: String, css: String): String = {
    val doc = document(html)
    val rules = stylesheet(css)
    rules.foreach(run(doc, _)) // ಥ﹏ಥ
    doc.outerHtml
  }

}