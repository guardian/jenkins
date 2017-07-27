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
import play.twirl.api.{Html, JavaScript, Css}

abstract class DefaultResource[A <: AtomData] extends Resource[A] {
  def css(atom: Atom) = None
  def js(atom: Atom) = None
}

object DefaultResource {
  implicit case object CTADefaultResource extends DefaultResource[AtomData.Cta] {
    def html_impl = (atom, data) => cta.default.html.index(atom, data)
  }

  implicit case object ExplainerDefaultResource extends DefaultResource[AtomData.Explainer] {
    def html_impl = (atom, data) => explainer.default.html.index(atom, data)
  }

  implicit case object GuideDefaultResource extends DefaultResource[AtomData.Guide] {
    def html_impl = (atom, data) => guide.default.html.index(atom, data)
  }

  implicit case object InteractiveDefaultResource extends DefaultResource[AtomData.Interactive] {
    def html_impl = (atom, data) => interactive.default.html.index(atom, data)
  }

  implicit case object MediaDefaultResource extends DefaultResource[AtomData.Media] {
    def html_impl = (atom, data) => media.default.html.index(atom, data)
  }

  implicit case object ProfileDefaultResource extends DefaultResource[AtomData.Profile] {
    def html_impl = (atom, data) => profile.default.html.index(atom, data)
  }

  implicit case object QandaDefaultResource extends DefaultResource[AtomData.Qanda] {
    def html_impl = (atom, data) => qanda.default.html.index(atom, data)
  }

  implicit case object QuizDefaultResource extends DefaultResource[AtomData.Quiz] {
    def html_impl = (atom, data) => quiz.default.html.index(atom, data)
  }

  implicit case object RecipeDefaultResource extends DefaultResource[AtomData.Recipe] {
    def html_impl = (atom, data) => recipe.default.html.index(atom, data)
  }

  implicit case object ReviewDefaultResource extends DefaultResource[AtomData.Review] {
    def html_impl = (atom, data) => review.default.html.index(atom, data)
  }

  implicit case object StoryquestionsDefaultResource extends DefaultResource[AtomData.Storyquestions] {
    def html_impl = (atom, data) => storyquestions.default.html.index(atom, data)
  }

  implicit case object TimelineDefaultResource extends DefaultResource[AtomData.Timeline] {
    def html_impl = (atom, data) => timeline.default.html.index(atom, data)
  }
}
