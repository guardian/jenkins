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
    def html_impl = (atom, data) => cta.article.html.index(atom, data)
    def css_impl = (atom, data) => cta.article.css.index(atom, data)
    def js_impl = (atom, data) => cta.article.js.index(atom, data)
  }

  implicit case object ExplainerArticleRendering extends ArticleRendering[AtomData.Explainer] {
    def html_impl = (atom, data) => explainer.article.html.index(atom, data)
    def css_impl = (atom, data) => explainer.article.css.index(atom, data)
    def js_impl = (atom, data) => explainer.article.js.index(atom, data)
  }

  implicit case object GuideArticleRendering extends ArticleRendering[AtomData.Guide] {
    def html_impl = (atom, data) => guide.article.html.index(atom, data)
    def css_impl = (atom, data) => guide.article.css.index(atom, data)
    def js_impl = (atom, data) => guide.article.js.index(atom, data)
  }

  implicit case object InteractiveArticleRendering extends ArticleRendering[AtomData.Interactive] {
    def html_impl = (atom, data) => interactive.article.html.index(atom, data)
    def css_impl = (atom, data) => interactive.article.css.index(atom, data)
    def js_impl = (atom, data) => interactive.article.js.index(atom, data)
  }

  implicit case object MediaArticleRendering extends ArticleRendering[AtomData.Media] {
    def html_impl = (atom, data) => media.article.html.index(atom, data)
    def css_impl = (atom, data) => media.article.css.index(atom, data)
    def js_impl = (atom, data) => media.article.js.index(atom, data)
  }

  implicit case object ProfileArticleRendering extends ArticleRendering[AtomData.Profile] {
    def html_impl = (atom, data) => profile.article.html.index(atom, data)
    def css_impl = (atom, data) => profile.article.css.index(atom, data)
    def js_impl = (atom, data) => profile.article.js.index(atom, data)
  }

  implicit case object QandaArticleRendering extends ArticleRendering[AtomData.Qanda] {
    def html_impl = (atom, data) => qanda.article.html.index(atom, data)
    def css_impl = (atom, data) => qanda.article.css.index(atom, data)
    def js_impl = (atom, data) => qanda.article.js.index(atom, data)
  }

  implicit case object QuizArticleRendering extends ArticleRendering[AtomData.Quiz] {
    def html_impl = (atom, data) => quiz.article.html.index(atom, data)
    def css_impl = (atom, data) => quiz.article.css.index(atom, data)
    def js_impl = (atom, data) => quiz.article.js.index(atom, data)
  }

  implicit case object RecipeArticleRendering extends ArticleRendering[AtomData.Recipe] {
    def html_impl = (atom, data) => recipe.article.html.index(atom, data)
    def css_impl = (atom, data) => recipe.article.css.index(atom, data)
    def js_impl = (atom, data) => recipe.article.js.index(atom, data)
  }

  implicit case object ReviewArticleRendering extends ArticleRendering[AtomData.Review] {
    def html_impl = (atom, data) => review.article.html.index(atom, data)
    def css_impl = (atom, data) => review.article.css.index(atom, data)
    def js_impl = (atom, data) => review.article.js.index(atom, data)
  }

  implicit case object StoryquestionsArticleRendering extends ArticleRendering[AtomData.Storyquestions] {
    def html_impl = (atom, data) => storyquestions.article.html.index(atom, data)
    def css_impl = (atom, data) => storyquestions.article.css.index(atom, data)
    def js_impl = (atom, data) => storyquestions.article.js.index(atom, data)
  }

  implicit case object TimelineArticleRendering extends ArticleRendering[AtomData.Timeline] {
    def html_impl = (atom, data) => timeline.article.html.index(atom, data)
    def css_impl = (atom, data) => timeline.article.css.index(atom, data)
    def js_impl = (atom, data) => timeline.article.js.index(atom, data)
  }
}
