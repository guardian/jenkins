package com.gu.contentatom.renderer
package renderers

import com.gu.contentatom.thrift.Atom

import play.twirl.api.Html

trait Rendering[A] {
  def html(atom: Atom, data: A): Html
  def css: Option[String]
  def js: Option[String]
}
