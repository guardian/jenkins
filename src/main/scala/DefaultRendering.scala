package jenkins

import com.gu.contentatom.thrift.Atom
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
import play.twirl.api.Html

trait DefaultRendering[A] extends Rendering[A] {
  def html(atom: Atom, data: A) = html_impl(atom, data)
  def css = None
  def js = None

  def html_impl: (Atom, A) => Html
}

object DefaultRenderings extends Renderings {
  val ctaRendering = new DefaultRendering[CTAAtom] {
    def html_impl = (atom, data) => cta.default.html.index(atom, data)
  }

  val explainerRendering = new DefaultRendering[ExplainerAtom] {
    def html_impl = (atom, data) => explainer.default.html.index(atom, data)
  }

  val guideRendering = new DefaultRendering[GuideAtom] {
    def html_impl = (atom, data) => guide.default.html.index(atom, data)
  }

  val interactiveRendering = new DefaultRendering[InteractiveAtom] {
    def html_impl = (atom, data) => interactive.default.html.index(atom, data)
  }

  val mediaRendering = new DefaultRendering[MediaAtom] {
    def html_impl = (atom, data) => media.default.html.index(atom, data)
  }

  val profileRendering = new DefaultRendering[ProfileAtom] {
    def html_impl = (atom, data) => profile.default.html.index(atom, data)
  }

  val qandaRendering = new DefaultRendering[QAndAAtom] {
    def html_impl = (atom, data) => qanda.default.html.index(atom, data)
  }

  val quizRendering = new DefaultRendering[QuizAtom] {
    def html_impl = (atom, data) => quiz.default.html.index(atom, data)
  }

  val recipeRendering = new DefaultRendering[RecipeAtom] {
    def html_impl = (atom, data) => recipe.default.html.index(atom, data)
  }

  val reviewRendering = new DefaultRendering[ReviewAtom] {
    def html_impl = (atom, data) => review.default.html.index(atom, data)
  }

  val storyquestionsRendering = new DefaultRendering[StoryQuestionsAtom] {
    def html_impl = (atom, data) => storyquestions.default.html.index(atom, data)
  }

  val timelineRendering = new DefaultRendering[TimelineAtom] {
    def html_impl = (atom, data) => timeline.default.html.index(atom, data)
  }
}
