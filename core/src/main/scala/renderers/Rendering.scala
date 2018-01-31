package com.gu.contentatom.renderer
package renderers

import com.gu.contentatom.thrift.Atom

import play.twirl.api.Html

trait Rendering[A] {
  type Conf

  def html[C <: Conf](atom: Atom, data: A)(implicit conf: C): Html
  def css: Option[String]
  def js: Option[String]
}
