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
  implicit def resource[A <: AtomData, B <: ThriftStruct](
    implicit
    impl: DefaultResourceImpl[A, B],
    reader: AtomReader.Aux[A, B]
  ): DefaultResource[A] = new DefaultResource[A] {
    def html(atom: Atom) = impl.html(atom, reader.splat(atom))
  }
}

trait DefaultResourceImpl[A <: AtomData, B <: ThriftStruct] extends {
  def html: (Atom, B) => Html
}

object DefaultResourceImpl {
  implicit case object CTADefaultResource extends DefaultResourceImpl[AtomData.Cta, CTAAtom] {
    def html = (atom, data) => cta.default.html.index(atom, data)
  }

  implicit case object ExplainerDefaultResource extends DefaultResourceImpl[AtomData.Explainer, ExplainerAtom] {
    def html = (atom, data) => explainer.default.html.index(atom, data)
  }

  implicit case object GuideDefaultResource extends DefaultResourceImpl[AtomData.Guide, GuideAtom] {
    def html = (atom, data) => guide.default.html.index(atom, data)
  }

  implicit case object InteractiveDefaultResource extends DefaultResourceImpl[AtomData.Interactive, InteractiveAtom] {
    def html = (atom, data) => interactive.default.html.index(atom, data)
  }

  implicit case object MediaDefaultResource extends DefaultResourceImpl[AtomData.Media, MediaAtom] {
    def html = (atom, data) => media.default.html.index(atom, data)
  }

  implicit case object ProfileDefaultResource extends DefaultResourceImpl[AtomData.Profile, ProfileAtom] {
    def html = (atom, data) => profile.default.html.index(atom, data)
  }

  implicit case object QandaDefaultResource extends DefaultResourceImpl[AtomData.Qanda, QAndAAtom] {
    def html = (atom, data) => qanda.default.html.index(atom, data)
  }

  implicit case object QuizDefaultResource extends DefaultResourceImpl[AtomData.Quiz, QuizAtom] {
    def html = (atom, data) => quiz.default.html.index(atom, data)
  }

  implicit case object RecipeDefaultResource extends DefaultResourceImpl[AtomData.Recipe, RecipeAtom] {
    def html = (atom, data) => recipe.default.html.index(atom, data)
  }

  implicit case object ReviewDefaultResource extends DefaultResourceImpl[AtomData.Review, ReviewAtom] {
    def html = (atom, data) => review.default.html.index(atom, data)
  }

  implicit case object StoryquestionsDefaultResource extends DefaultResourceImpl[AtomData.Storyquestions, StoryQuestionsAtom] {
    def html = (atom, data) => storyquestions.default.html.index(atom, data)
  }

  implicit case object TimelineDefaultResource extends DefaultResourceImpl[AtomData.Timeline, TimelineAtom] {
    def html = (atom, data) => timeline.default.html.index(atom, data)
  }
}
