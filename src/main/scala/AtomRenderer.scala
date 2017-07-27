package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData, AtomType}

trait AtomRenderer {
  type HTML = String
  type CSS = Option[String]
  type JS = Option[String]

  def getAll[A <: AtomData : Resource](atom: Atom): (HTML, CSS, JS) =
    (getHTML(atom), getCSS(atom), getJS(atom))

  def getHTML[A <: AtomData](atom: Atom)(implicit r: Resource[A]): HTML =
    r.html(atom).toString

  def getCSS[A <: AtomData](atom: Atom)(implicit r: Resource[A]): CSS =
    r.css(atom).map(_.toString)

  def getJS[A <: AtomData](atom: Atom)(implicit r: Resource[A]): JS =
    r.js(atom).map(_.toString)

  def getDefaultHTML(atom: Atom): HTML = atom.defaultHtml
}

object AtomRenderer extends AtomRenderer {
  def apply(atom: Atom) = atom.atomType match {
    case AtomType.Cta            => getAll[AtomData.Cta]           (atom)
    case AtomType.Explainer      => getAll[AtomData.Explainer]     (atom)
    case AtomType.Guide          => getAll[AtomData.Guide]         (atom)
    case AtomType.Interactive    => getAll[AtomData.Interactive]   (atom)
    case AtomType.Media          => getAll[AtomData.Media]         (atom)
    case AtomType.Profile        => getAll[AtomData.Profile]       (atom)
    case AtomType.Qanda          => getAll[AtomData.Qanda]         (atom)
    case AtomType.Quiz           => getAll[AtomData.Quiz]          (atom)
    case AtomType.Recipe         => getAll[AtomData.Recipe]        (atom)
    case AtomType.Review         => getAll[AtomData.Review]        (atom)
    case AtomType.Storyquestions => getAll[AtomData.Storyquestions](atom)
    case AtomType.Timeline       => getAll[AtomData.Timeline]      (atom)
    case _                       => (getDefaultHTML(atom), None, None)
  }
}

trait DefaultAtomRenderer extends AtomRenderer {
  def getHTML[A <: AtomData](atom: Atom)(implicit r: DefaultResource[A]): HTML =
    r.html(atom).toString
}
