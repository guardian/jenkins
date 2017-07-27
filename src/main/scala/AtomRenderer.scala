package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData, AtomType}
import play.twirl.api.{Html, JavaScript, Css}

trait AtomRenderer[F[_] <: Rendering[_]] {
  type HTML = String
  type CSS = Option[String]
  type JS = Option[String]

  def getHTML[A](atom: Atom, data: A)(implicit reader: F[A]): HTML
  def getCSS[A](atom: Atom, data: A)(implicit reader: F[A]): CSS
  def getJS[A](atom: Atom, data: A)(implicit reader: F[A]): JS

  def getAll[A : F](atom: Atom, data: A): (HTML, CSS, JS) =
    (getHTML(atom, data), getCSS(atom, data), getJS(atom, data))
}

object ArticleAtomRender extends AtomRenderer[ArticleRendering] {
  def apply(atom: Atom) = atom.data match {
    case AtomData.Cta(data)            => getAll(atom, data)
    case AtomData.Explainer(data)      => getAll(atom, data)
    case AtomData.Guide(data)          => getAll(atom, data)
    case AtomData.Interactive(data)    => getAll(atom, data)
    case AtomData.Media(data)          => getAll(atom, data)
    case AtomData.Profile (data)       => getAll(atom, data)
    case AtomData.Qanda(data)          => getAll(atom, data)
    case AtomData.Quiz(data)           => getAll(atom, data)
    case AtomData.Recipe(data)         => getAll(atom, data)
    case AtomData.Review(data)         => getAll(atom, data)
    case AtomData.Storyquestions(data) => getAll(atom, data)
    case AtomData.Timeline(data)       => getAll(atom, data)
    case _                             => (atom.defaultHtml, None, None)
  }

  def getHTML[A](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): HTML =
    reader.html(atom, data).toString

  def getCSS[A](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): CSS =
    reader.css(atom, data).map(_.toString)

  def getJS[A](atom: Atom, data: A)(implicit reader: ArticleRendering[A]): JS =
    reader.js(atom, data).map(_.toString)
}

object DefaultAtomRender extends AtomRenderer[DefaultRendering] {
  def apply(atom: Atom) = atom.data match {
    case AtomData.Cta(data)            => getAll(atom, data)
    case AtomData.Explainer(data)      => getAll(atom, data)
    case AtomData.Guide(data)          => getAll(atom, data)
    case AtomData.Interactive(data)    => getAll(atom, data)
    case AtomData.Media(data)          => getAll(atom, data)
    case AtomData.Profile (data)       => getAll(atom, data)
    case AtomData.Qanda(data)          => getAll(atom, data)
    case AtomData.Quiz(data)           => getAll(atom, data)
    case AtomData.Recipe(data)         => getAll(atom, data)
    case AtomData.Review(data)         => getAll(atom, data)
    case AtomData.Storyquestions(data) => getAll(atom, data)
    case AtomData.Timeline(data)       => getAll(atom, data)
    case _                             => (atom.defaultHtml, None, None)
  }

  def getHTML[A](atom: Atom, data: A)(implicit reader: DefaultRendering[A]): HTML =
    reader.html(atom, data).toString

  def getCSS[A](atom: Atom, data: A)(implicit reader: DefaultRendering[A]): CSS =
    reader.css(atom, data).map(_.toString)

  def getJS[A](atom: Atom, data: A)(implicit reader: DefaultRendering[A]): JS =
    reader.js(atom, data).map(_.toString)
}
