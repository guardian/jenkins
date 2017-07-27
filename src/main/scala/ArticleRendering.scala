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

class ArticleRendering[A <: AtomData] extends Rendering[A]

object ArticleRendering {
  implicit case object CTAArticleRendering extends ArticleRendering[AtomData.Cta] {
    def html_impl = (atom, data) => cta.html.index(atom, data)
    def css_impl = (atom, data) => cta.css.index(atom, data)
    def js_impl = (atom, data) => cta.js.index(atom, data)
  }

  implicit case object ExplainerArticleRendering extends ArticleRendering[AtomData.Explainer] {
    def html_impl = (atom, data) => explainer.html.index(atom, data)
    def css_impl = (atom, data) => explainer.css.index(atom, data)
    def js_impl = (atom, data) => explainer.js.index(atom, data)
  }

  implicit case object GuideArticleRendering extends ArticleRendering[AtomData.Guide] {
    def html_impl = (atom, data) => guide.html.index(atom, data)
    def css_impl = (atom, data) => guide.css.index(atom, data)
    def js_impl = (atom, data) => guide.js.index(atom, data)
  }

  implicit case object InteractiveArticleRendering extends ArticleRendering[AtomData.Interactive] {
    def html_impl = (atom, data) => interactive.html.index(atom, data)
    def css_impl = (atom, data) => interactive.css.index(atom, data)
    def js_impl = (atom, data) => interactive.js.index(atom, data)
  }

  implicit case object MediaArticleRendering extends ArticleRendering[AtomData.Media] {
    def html_impl = (atom, data) => media.html.index(atom, data)
    def css_impl = (atom, data) => media.css.index(atom, data)
    def js_impl = (atom, data) => media.js.index(atom, data)
  }

  implicit case object ProfileArticleRendering extends ArticleRendering[AtomData.Profile] {
    def html_impl = (atom, data) => profile.html.index(atom, data)
    def css_impl = (atom, data) => profile.css.index(atom, data)
    def js_impl = (atom, data) => profile.js.index(atom, data)
  }

  implicit case object QandaArticleRendering extends ArticleRendering[AtomData.Qanda] {
    def html_impl = (atom, data) => qanda.html.index(atom, data)
    def css_impl = (atom, data) => qanda.css.index(atom, data)
    def js_impl = (atom, data) => qanda.js.index(atom, data)
  }

  implicit case object QuizArticleRendering extends ArticleRendering[AtomData.Quiz] {
    def html_impl = (atom, data) => quiz.html.index(atom, data)
    def css_impl = (atom, data) => quiz.css.index(atom, data)
    def js_impl = (atom, data) => quiz.js.index(atom, data)
  }

  implicit case object RecipeArticleRendering extends ArticleRendering[AtomData.Recipe] {
    def html_impl = (atom, data) => recipe.html.index(atom, data)
    def css_impl = (atom, data) => recipe.css.index(atom, data)
    def js_impl = (atom, data) => recipe.js.index(atom, data)
  }

  implicit case object ReviewArticleRendering extends ArticleRendering[AtomData.Review] {
    def html_impl = (atom, data) => review.html.index(atom, data)
    def css_impl = (atom, data) => review.css.index(atom, data)
    def js_impl = (atom, data) => review.js.index(atom, data)
  }

  implicit case object StoryquestionsArticleRendering extends ArticleRendering[AtomData.Storyquestions] {
    def html_impl = (atom, data) => storyquestions.html.index(atom, data)
    def css_impl = (atom, data) => storyquestions.css.index(atom, data)
    def js_impl = (atom, data) => storyquestions.js.index(atom, data)
  }

  implicit case object TimelineArticleRendering extends ArticleRendering[AtomData.Timeline] {
    def html_impl = (atom, data) => timeline.html.index(atom, data)
    def css_impl = (atom, data) => timeline.css.index(atom, data)
    def js_impl = (atom, data) => timeline.js.index(atom, data)
  }
}
