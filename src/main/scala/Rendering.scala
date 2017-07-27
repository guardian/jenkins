package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData}
import play.twirl.api.{Html, JavaScript, Css}

abstract class Rendering[A <: AtomData](implicit val reader: AtomReader[A]) {
  def html(atom: Atom): Html = html_impl(atom, reader.splat(atom))
  def css(atom: Atom): Option[Css] = Some(css_impl(atom, reader.splat(atom)))
  def js(atom: Atom): Option[JavaScript] = Some(js_impl(atom, reader.splat(atom)))

  protected def html_impl: (Atom, reader.AtomType) => Html
  protected def css_impl: (Atom, reader.AtomType) => Css
  protected def js_impl: (Atom, reader.AtomType) => JavaScript
}
