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
}

object AtomRenderer extends AtomRenderer {
  def apply(atom: Atom) = atom.atomType match {
    case AtomType.Cta            => Some(getAll[AtomData.Cta]           (atom))
    case AtomType.Explainer      => Some(getAll[AtomData.Explainer]     (atom))
    case AtomType.Guide          => Some(getAll[AtomData.Guide]         (atom))
    case AtomType.Interactive    => Some(getAll[AtomData.Interactive]   (atom))
    case AtomType.Media          => Some(getAll[AtomData.Media]         (atom))
    case AtomType.Profile        => Some(getAll[AtomData.Profile]       (atom))
    case AtomType.Qanda          => Some(getAll[AtomData.Qanda]         (atom))
    case AtomType.Quiz           => Some(getAll[AtomData.Quiz]          (atom))
    case AtomType.Recipe         => Some(getAll[AtomData.Recipe]        (atom))
    case AtomType.Review         => Some(getAll[AtomData.Review]        (atom))
    case AtomType.Storyquestions => Some(getAll[AtomData.Storyquestions](atom))
    case AtomType.Timeline       => Some(getAll[AtomData.Timeline]      (atom))
    case _ => None
  }
}

trait DefaultAtomRenderer extends AtomRenderer {
  def getHTML[A <: AtomData](atom: Atom)(implicit r: DefaultResource[A]): HTML =
    r.html(atom).toString
}
