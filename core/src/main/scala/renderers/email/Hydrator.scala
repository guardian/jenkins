package com.gu.contentatom.renderer
package renderers
package email

import org.jsoup.Jsoup
import org.jsoup.nodes.{ Document, Element }
import org.jsoup.safety.Whitelist

import collection.JavaConverters._

trait Hydrator {
  val whitelist = Whitelist.basicWithImages()
    .addTags("table", "tr", "td")

  val document: String => Document = 
    doc => Jsoup.parse(Jsoup.clean(doc, whitelist))
 
  val query: Element => CSSSelector => List[Element] = 
    el => sel => el.select(sel.value).asScala.toList
  
  val update: Element => CSSProperty => CSSValue => Element =
    el => prop => value => el.attr("style", s"""${prop}:${value};${el.attr("style")}""")

  val run: Document => CSSRuleset => Document =
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