package jenkins

import com.gu.contentatom.thrift.{Atom, AtomData}
import com.gu.contentatom.thrift.atom.cta.CTAAtom
import com.gu.contentatom.thrift.atom.explainer.ExplainerAtom
import com.gu.contentatom.thrift.atom.guide.GuideAtom
import com.gu.contentatom.thrift.atom.interactive.InteractiveAtom
import com.gu.contentatom.thrift.atom.media.MediaAtom
import com.gu.contentatom.thrift.atom.profile.ProfileAtom
import com.gu.contentatom.thrift.atom.qanda.QAndAAtom
import com.gu.contentatom.thrift.atom.quiz.QuizAtom
import com.gu.contentatom.thrift.atom.recipe.RecipeAtom
import com.gu.contentatom.thrift.atom.review.ReviewAtom
import com.gu.contentatom.thrift.atom.storyquestions.StoryQuestionsAtom
import com.gu.contentatom.thrift.atom.timeline.TimelineAtom
import com.twitter.scrooge.ThriftStruct

/**
 * AtomReader
 *
 * Provides easy access to the data inside an atom, without
 * having to pattern match against the whole `AtomData`/`AtomType` ADT.
 *
 * It is painful to write any code involving Thrift data structures generated
 * by Scrooge without having to write a lot of boilerplate, or resort to
 * macros. In this library, we don't want any of that, so we'll abstract the
 * extraction process and use the type system to do the work for us.
 *
 * To use `AtomReader`, just summon an implicit `AtomReader.Aux[A, B]` where
 * - `A` is the subtype of `AtomData` you're interested in
 * - `B` is the corresponding `XYZAtom` struct
 * and then call splat, passing the atom as an argument.
 *
 * New atoms need their own implicit object below. Let's say we invent a
 * `MapAtom` someday, accessible via `map` in the `AtomData` struct then
 * it's just a matter of writing:
 *
 * {{{
 * implicit object MapAtomReader extends AtomReader[AtomData.Map] {
 *   type AtomType = MapAtom
 *   def splat(atom: Atom) = data(atom).map
 * }
 * }}}
 *
 */

trait AtomReader[A <: AtomData] {
  type AtomType <: ThriftStruct

  def splat(atom: Atom): AtomType

  def data(atom: Atom): A = atom.data.asInstanceOf[A]
}

object AtomReader {
  type Aux[A <: AtomData, B <: ThriftStruct] = AtomReader[A] { type AtomType = B }

  implicit case object CTAAtomReader extends AtomReader[AtomData.Cta] {
    type AtomType = CTAAtom
    def splat(atom: Atom) = data(atom).cta
  }

  implicit case object ExplainerAtomReader extends AtomReader[AtomData.Explainer] {
    type AtomType = ExplainerAtom
    def splat(atom: Atom) = data(atom).explainer
  }

  implicit case object GuideAtomReader extends AtomReader[AtomData.Guide] {
    type AtomType = GuideAtom
    def splat(atom: Atom) = data(atom).guide
  }

  implicit case object InteractiveAtomReader extends AtomReader[AtomData.Interactive] {
    type AtomType = InteractiveAtom
    def splat(atom: Atom) = data(atom).interactive
  }

  implicit case object MediaAtomReader extends AtomReader[AtomData.Media] {
    type AtomType = MediaAtom
    def splat(atom: Atom) = data(atom).media
  }

  implicit case object ProfileAtomReader extends AtomReader[AtomData.Profile] {
    type AtomType = ProfileAtom
    def splat(atom: Atom) = data(atom).profile
  }

  implicit case object QandaAtomReader extends AtomReader[AtomData.Qanda] {
    type AtomType = QAndAAtom
    def splat(atom: Atom) = data(atom).qanda
  }

  implicit case object QuizAtomReader extends AtomReader[AtomData.Quiz] {
    type AtomType = QuizAtom
    def splat(atom: Atom) = data(atom).quiz
  }

  implicit case object RecipeAtomReader extends AtomReader[AtomData.Recipe] {
    type AtomType = RecipeAtom
    def splat(atom: Atom) = data(atom).recipe
  }

  implicit case object ReviewAtomReader extends AtomReader[AtomData.Review] {
    type AtomType = ReviewAtom
    def splat(atom: Atom) = data(atom).review
  }

  implicit case object StoryquestionsAtomReader extends AtomReader[AtomData.Storyquestions] {
    type AtomType = StoryQuestionsAtom
    def splat(atom: Atom) = data(atom).storyquestions
  }

  implicit case object TimelineAtomReader extends AtomReader[AtomData.Timeline] {
    type AtomType = TimelineAtom
    def splat(atom: Atom) = data(atom).timeline
  }

}
