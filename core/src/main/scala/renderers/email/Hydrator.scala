package com.gu.contentatom.renderer
package renderers
package email

import org.jsoup.Jsoup
import org.jsoup.nodes.{ Document, Element }
import org.jsoup.safety.Whitelist

import collection.JavaConverters._

trait Hydrator {
  def whitelist = Whitelist.basicWithImages()
    .addTags("table", "tr", "td")

  def document: String => Document = 
    doc => Jsoup.parse(Jsoup.clean(doc, whitelist))
 
  def stylesheet: String => List[CSSRuleset] =
    CSS.parse(_)
 
  def query: Element => CSSSelector => List[Element] = 
    el => sel => el.select(sel.value).asScala.toList
  
  def update: Element => CSSProperty => CSSValue => Element =
    el => prop => value => el.attr("style", s"""${prop}:${value};${el.attr("style")}""")

  def run: Document => CSSRuleset => Document =
    doc => rules => {
      for {
        selector <- rules.selectors
        element <- query(doc)(selector)
        (prop, value) <- rules.block.decls
      } {
        update(element)(prop)(value)
      }
      doc
    }

  def apply(html: String, css: String): String = {
    val doc = document(html)
    for {
      rules <- CSS.parse(css)
    } {
      run(doc)(rules)
    }
    doc.outerHtml
  }

}