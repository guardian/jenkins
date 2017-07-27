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

abstract class DefaultRendering[A <: AtomData] extends Rendering[A] {
  def css(atom: Atom) = None
  def js(atom: Atom) = None
}

object DefaultRendering {
  implicit case object CTADefaultRendering extends DefaultRendering[AtomData.Cta] {
    def html_impl = (atom, data) => cta.default.html.index(atom, data)
  }

  implicit case object ExplainerDefaultRendering extends DefaultRendering[AtomData.Explainer] {
    def html_impl = (atom, data) => explainer.default.html.index(atom, data)
  }

  implicit case object GuideDefaultRendering extends DefaultRendering[AtomData.Guide] {
    def html_impl = (atom, data) => guide.default.html.index(atom, data)
  }

  implicit case object InteractiveDefaultRendering extends DefaultRendering[AtomData.Interactive] {
    def html_impl = (atom, data) => interactive.default.html.index(atom, data)
  }

  implicit case object MediaDefaultRendering extends DefaultRendering[AtomData.Media] {
    def html_impl = (atom, data) => media.default.html.index(atom, data)
  }

  implicit case object ProfileDefaultRendering extends DefaultRendering[AtomData.Profile] {
    def html_impl = (atom, data) => profile.default.html.index(atom, data)
  }

  implicit case object QandaDefaultRendering extends DefaultRendering[AtomData.Qanda] {
    def html_impl = (atom, data) => qanda.default.html.index(atom, data)
  }

  implicit case object QuizDefaultRendering extends DefaultRendering[AtomData.Quiz] {
    def html_impl = (atom, data) => quiz.default.html.index(atom, data)
  }

  implicit case object RecipeDefaultRendering extends DefaultRendering[AtomData.Recipe] {
    def html_impl = (atom, data) => recipe.default.html.index(atom, data)
  }

  implicit case object ReviewDefaultRendering extends DefaultRendering[AtomData.Review] {
    def html_impl = (atom, data) => review.default.html.index(atom, data)
  }

  implicit case object StoryquestionsDefaultRendering extends DefaultRendering[AtomData.Storyquestions] {
    def html_impl = (atom, data) => storyquestions.default.html.index(atom, data)
  }

  implicit case object TimelineDefaultRendering extends DefaultRendering[AtomData.Timeline] {
    def html_impl = (atom, data) => timeline.default.html.index(atom, data)
  }
}
