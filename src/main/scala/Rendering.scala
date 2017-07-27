package jenkins

import com.gu.contentatom.thrift.Atom
import play.twirl.api.{Html, JavaScript, Css}

trait Rendering[A] {
  def html: (Atom, A) => Html
  def css: (Atom, A) => Option[Css]
  def js: (Atom, A) => Option[JavaScript]
}
