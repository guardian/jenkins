package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData, AtomType}
import com.gu.contentatom.thrift.atom.cta.CTAAtom
import com.twitter.scrooge.ThriftStruct

trait AtomRenderer {
  type HTML = String
  type CSS = Option[String]
  type JS = Option[String]

  def apply(atom: Atom) = atom.data match {
    case AtomData.Cta(d)            => getAll(atom, d)
    case AtomData.Explainer(d)      => getAll(atom, d)
    case AtomData.Guide(d)          => getAll(atom, d)
    case AtomData.Interactive(d)    => getAll(atom, d)
    case AtomData.Media(d)          => getAll(atom, d)
    case AtomData.Profile (d)       => getAll(atom, d)
    case AtomData.Qanda(d)          => getAll(atom, d)
    case AtomData.Quiz(d)           => getAll(atom, d)
    case AtomData.Recipe(d)         => getAll(atom, d)
    case AtomData.Review(d)         => getAll(atom, d)
    case AtomData.Storyquestions(d) => getAll(atom, d)
    case AtomData.Timeline(d)       => getAll(atom, d)
    case _                          => (atom.defaultHtml, None, None)
  }

  def getHTML[A <: ThriftStruct](atom: Atom, data: A, r: Rendering[A]): HTML =
    r.html(atom, data).toString

  def getHTML[A <: ThriftStruct](atom: Atom, data: A): HTML

  def getCSS[A <: ThriftStruct](atom: Atom, data: A, r: Rendering[A]): CSS =
    r.css(atom, data).map(_.toString)

  def getCSS[A <: ThriftStruct](atom: Atom, data: A): CSS

  def getJS[A <: ThriftStruct](atom: Atom, data: A, r: Rendering[A]): JS =
    r.js(atom, data).map(_.toString)

  def getJS[A <: ThriftStruct](atom: Atom, data: A): JS

  def getAll[A <: ThriftStruct](atom: Atom, data: A): (HTML, CSS, JS) =
    (getHTML(atom, data), getCSS(atom, data), getJS(atom, data))
}

trait ArticleAtomRenderer extends AtomRenderer {
  def getHTML[A <: ThriftStruct](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): HTML =
    getHTML(atom, data, reader)

  def getCSS[A <: ThriftStruct](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): CSS =
    getCSS(atom, data, reader)

  def getJS[A <: ThriftStruct](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): JS =
    getJS(atom, data, reader)
}

trait DefaultAtomRenderer extends AtomRenderer {
  def getHTML[A <: ThriftStruct](atom: Atom, data: A)(implicit r: DefaultRendering[A]): HTML =
    r.html(atom, data).toString
}
