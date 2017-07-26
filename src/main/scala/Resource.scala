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

abstract class Resource[A] {
  def html(atom: Atom): Html
  def css(atom: Atom): Option[Css]
  def js(atom: Atom): Option[JavaScript]
}

object Resource {
  implicit def resource[A <: AtomData, B <: ThriftStruct](
    implicit
    impl: ResourceImpl[A, B],
    reader: AtomReader.Aux[A, B]
  ): Resource[A] = new Resource[A] {
    def html(atom: Atom) = impl.html(atom, reader.splat(atom))
    def css(atom: Atom) = Some(impl.css(atom, reader.splat(atom)))
    def js(atom: Atom) = Some(impl.js(atom, reader.splat(atom)))
  }
}

trait ResourceImpl[A <: AtomData, B <: ThriftStruct] {
  def html: (Atom, B) => Html
  def css: (Atom, B) => Css
  def js: (Atom, B) => JavaScript
}

object ResourceImpl {
  implicit case object CTAResource extends ResourceImpl[AtomData.Cta, CTAAtom] {
    def html = (atom, data) => cta.html.index(atom, data)
    def css = (atom, data) => cta.css.index(atom, data)
    def js = (atom, data) => cta.js.index(atom, data)
  }

  implicit case object ExplainerResource extends ResourceImpl[AtomData.Explainer, ExplainerAtom] {
    def html = (atom, data) => explainer.html.index(atom, data)
    def css = (atom, data) => explainer.css.index(atom, data)
    def js = (atom, data) => explainer.js.index(atom, data)
  }

  implicit case object GuideResource extends ResourceImpl[AtomData.Guide, GuideAtom] {
    def html = (atom, data) => guide.html.index(atom, data)
    def css = (atom, data) => guide.css.index(atom, data)
    def js = (atom, data) => guide.js.index(atom, data)
  }

  implicit case object InteractiveResource extends ResourceImpl[AtomData.Interactive, InteractiveAtom] {
    def html = (atom, data) => interactive.html.index(atom, data)
    def css = (atom, data) => interactive.css.index(atom, data)
    def js = (atom, data) => interactive.js.index(atom, data)
  }

  implicit case object MediaResource extends ResourceImpl[AtomData.Media, MediaAtom] {
    def html = (atom, data) => media.html.index(atom, data)
    def css = (atom, data) => media.css.index(atom, data)
    def js = (atom, data) => media.js.index(atom, data)
  }

  implicit case object ProfileResource extends ResourceImpl[AtomData.Profile, ProfileAtom] {
    def html = (atom, data) => profile.html.index(atom, data)
    def css = (atom, data) => profile.css.index(atom, data)
    def js = (atom, data) => profile.js.index(atom, data)
  }

  implicit case object QandaResource extends ResourceImpl[AtomData.Qanda, QAndAAtom] {
    def html = (atom, data) => qanda.html.index(atom, data)
    def css = (atom, data) => qanda.css.index(atom, data)
    def js = (atom, data) => qanda.js.index(atom, data)
  }

  implicit case object QuizResource extends ResourceImpl[AtomData.Quiz, QuizAtom] {
    def html = (atom, data) => quiz.html.index(atom, data)
    def css = (atom, data) => quiz.css.index(atom, data)
    def js = (atom, data) => quiz.js.index(atom, data)
  }

  implicit case object RecipeResource extends ResourceImpl[AtomData.Recipe, RecipeAtom] {
    def html = (atom, data) => recipe.html.index(atom, data)
    def css = (atom, data) => recipe.css.index(atom, data)
    def js = (atom, data) => recipe.js.index(atom, data)
  }

  implicit case object ReviewResource extends ResourceImpl[AtomData.Review, ReviewAtom] {
    def html = (atom, data) => review.html.index(atom, data)
    def css = (atom, data) => review.css.index(atom, data)
    def js = (atom, data) => review.js.index(atom, data)
  }

  implicit case object StoryquestionsResource extends ResourceImpl[AtomData.Storyquestions, StoryQuestionsAtom] {
    def html = (atom, data) => storyquestions.html.index(atom, data)
    def css = (atom, data) => storyquestions.css.index(atom, data)
    def js = (atom, data) => storyquestions.js.index(atom, data)
  }

  implicit case object TimelineResource extends ResourceImpl[AtomData.Timeline, TimelineAtom] {
    def html = (atom, data) => timeline.html.index(atom, data)
    def css = (atom, data) => timeline.css.index(atom, data)
    def js = (atom, data) => timeline.js.index(atom, data)
  }
}
