package com.gu.contentatom.renderer
package renderers

import com.gu.contentatom.thrift.Atom

import play.twirl.api.Html

trait Rendering[A] {
  type Conf <: Configuration

  def html(atom: Atom, data: A)(implicit conf: Conf): Html
  def css: Option[String]
  def js: Option[String]
}

object Rendering {
  type Aux[A, C] = Rendering[A] { type Conf = C }
}