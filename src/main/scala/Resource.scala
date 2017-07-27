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

abstract class Resource[A <: AtomData](implicit val reader: AtomReader[A]) {
  def html(atom: Atom): Html = html_impl(atom, reader.splat(atom))
  def css(atom: Atom): Option[Css] = Some(css_impl(atom, reader.splat(atom)))
  def js(atom: Atom): Option[JavaScript] = Some(js_impl(atom, reader.splat(atom)))

  protected def html_impl: (Atom, reader.AtomType) => Html
  protected def css_impl: (Atom, reader.AtomType) => Css
  protected def js_impl: (Atom, reader.AtomType) => JavaScript
}

object Resource {
  implicit case object CTAResource extends Resource[AtomData.Cta] {
    def html_impl = (atom, data) => cta.html.index(atom, data)
    def css_impl = (atom, data) => cta.css.index(atom, data)
    def js_impl = (atom, data) => cta.js.index(atom, data)
  }

  implicit case object ExplainerResource extends Resource[AtomData.Explainer] {
    def html_impl = (atom, data) => explainer.html.index(atom, data)
    def css_impl = (atom, data) => explainer.css.index(atom, data)
    def js_impl = (atom, data) => explainer.js.index(atom, data)
  }

  implicit case object GuideResource extends Resource[AtomData.Guide] {
    def html_impl = (atom, data) => guide.html.index(atom, data)
    def css_impl = (atom, data) => guide.css.index(atom, data)
    def js_impl = (atom, data) => guide.js.index(atom, data)
  }

  implicit case object InteractiveResource extends Resource[AtomData.Interactive] {
    def html_impl = (atom, data) => interactive.html.index(atom, data)
    def css_impl = (atom, data) => interactive.css.index(atom, data)
    def js_impl = (atom, data) => interactive.js.index(atom, data)
  }

  implicit case object MediaResource extends Resource[AtomData.Media] {
    def html_impl = (atom, data) => media.html.index(atom, data)
    def css_impl = (atom, data) => media.css.index(atom, data)
    def js_impl = (atom, data) => media.js.index(atom, data)
  }

  implicit case object ProfileResource extends Resource[AtomData.Profile] {
    def html_impl = (atom, data) => profile.html.index(atom, data)
    def css_impl = (atom, data) => profile.css.index(atom, data)
    def js_impl = (atom, data) => profile.js.index(atom, data)
  }

  implicit case object QandaResource extends Resource[AtomData.Qanda] {
    def html_impl = (atom, data) => qanda.html.index(atom, data)
    def css_impl = (atom, data) => qanda.css.index(atom, data)
    def js_impl = (atom, data) => qanda.js.index(atom, data)
  }

  implicit case object QuizResource extends Resource[AtomData.Quiz] {
    def html_impl = (atom, data) => quiz.html.index(atom, data)
    def css_impl = (atom, data) => quiz.css.index(atom, data)
    def js_impl = (atom, data) => quiz.js.index(atom, data)
  }

  implicit case object RecipeResource extends Resource[AtomData.Recipe] {
    def html_impl = (atom, data) => recipe.html.index(atom, data)
    def css_impl = (atom, data) => recipe.css.index(atom, data)
    def js_impl = (atom, data) => recipe.js.index(atom, data)
  }

  implicit case object ReviewResource extends Resource[AtomData.Review] {
    def html_impl = (atom, data) => review.html.index(atom, data)
    def css_impl = (atom, data) => review.css.index(atom, data)
    def js_impl = (atom, data) => review.js.index(atom, data)
  }

  implicit case object StoryquestionsResource extends Resource[AtomData.Storyquestions] {
    def html_impl = (atom, data) => storyquestions.html.index(atom, data)
    def css_impl = (atom, data) => storyquestions.css.index(atom, data)
    def js_impl = (atom, data) => storyquestions.js.index(atom, data)
  }

  implicit case object TimelineResource extends Resource[AtomData.Timeline] {
    def html_impl = (atom, data) => timeline.html.index(atom, data)
    def css_impl = (atom, data) => timeline.css.index(atom, data)
    def js_impl = (atom, data) => timeline.js.index(atom, data)
  }
}
