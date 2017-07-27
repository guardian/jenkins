package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData}
import com.twitter.scrooge.ThriftStruct
import play.twirl.api.{Html, JavaScript, Css}

abstract class Rendering[A <: ThriftStruct] {
  def html(atom: Atom, data: A): Html
  def css(atom: Atom, data: A): Option[Css]
  def js(atom: Atom, data: A): Option[JavaScript]
}
