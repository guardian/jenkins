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

  def apply: Atom => HTML = getHTML

  def getHTML[A](atom: Atom, data: A)(implicit reader: Rendering[A]): HTML =
    reader.html(atom, data).toString
  
  def getHTML(atom: Atom): HTML = atom.data match {
    case AtomData.Cta(data)            => getHTML(atom, data)
    case AtomData.Explainer(data)      => getHTML(atom, data)
    case AtomData.Guide(data)          => getHTML(atom, data)
    case AtomData.Interactive(data)    => getHTML(atom, data)
    case AtomData.Media(data)          => getHTML(atom, data)
    case AtomData.Profile (data)       => getHTML(atom, data)
    case AtomData.Qanda(data)          => getHTML(atom, data)
    case AtomData.Quiz(data)           => getHTML(atom, data)
    case AtomData.Recipe(data)         => getHTML(atom, data)
    case AtomData.Review(data)         => getHTML(atom, data)
    case AtomData.Storyquestions(data) => getHTML(atom, data)
    case AtomData.Timeline(data)       => getHTML(atom, data)
    case _                             => atom.defaultHtml
  }

  def getHTML(json: Json): Option[HTML] = json.as[Atom] match {
    case Left(_) => None
    case Right(atom) => Some(getHTML(atom))
  } 

  def getCSS[A](implicit reader: Rendering[A]): CSS =
    reader.css.map(_.toString)

  def getJS[A](implicit reader: Rendering[A]): JS =
    reader.js.map(_.toString)
}

object ArticleAtomRenderer extends AtomRenderer {
  val renderings = ArticleRenderings
}

object DefaultAtomRenderer extends AtomRenderer {
  val renderings = DefaultRenderings
}
