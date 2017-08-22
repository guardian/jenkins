package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData, AtomType}
import io.circe._

trait AtomRenderer {
  protected val renderings: Renderings
  import renderings._
  import json._

  type HTML = String
  type CSS = Option[String]
  type JS = Option[String]

  def apply: Atom => (HTML, CSS, JS) = getAll

  def getHTML[A](atom: Atom, data: A)(implicit reader: Rendering[A]): HTML =
    reader.html(atom, data).toString

  def getCSS[A](atom: Atom, data: A)(implicit reader: Rendering[A]): CSS =
    reader.css(atom, data).map(_.toString)

  def getJS[A](atom: Atom, data: A)(implicit reader: Rendering[A]): JS =
    reader.js(atom, data).map(_.toString)

  def getAll[A : Rendering](atom: Atom, data: A): (HTML, CSS, JS) =
    (getHTML(atom, data), getCSS(atom, data), getJS(atom, data))
  
  def getAll(atom: Atom): (HTML, CSS, JS) = atom.data match {
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

  def getAll(json: Json): Option[(HTML, CSS, JS)] = json.as[Atom] match {
    case Left(_) => None
    case Right(atom) => Some(getAll(atom))
  } 
}

object ArticleAtomRenderer extends AtomRenderer {
  val renderings = ArticleRenderings
}

object DefaultAtomRenderer extends AtomRenderer {
  val renderings = DefaultRenderings
}
