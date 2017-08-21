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
import play.twirl.api.{Html, JavaScript}
import twirl.Css

trait ArticleRendering[A] extends Rendering[A] {
  def html = html_impl
  def css = (atom, data) => Some(css_impl(atom, data))
  def js = (atom, data) => js_impl(atom, data)

  def html_impl: (Atom, A) => Html
  def css_impl: (Atom, A) => Css
  def js_impl: (Atom, A) => Option[String]
}

object ArticleRenderings extends Renderings {
  val ctaRendering = new ArticleRendering[CTAAtom] {
    def html_impl = (atom, data) => cta.article.html.index(atom, data)
    def css_impl = (atom, data) => cta.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/cta/article/index.js")
  }

  val explainerRendering = new ArticleRendering[ExplainerAtom] {
    def html_impl = (atom, data) => explainer.article.html.index(atom, data)
    def css_impl = (atom, data) => explainer.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/explainer/article/index.js")
  }

  val guideRendering = new ArticleRendering[GuideAtom] {
    def html_impl = (atom, data) => guide.article.html.index(atom, data)
    def css_impl = (atom, data) => guide.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/guide/article/index.js")
  }

  val interactiveRendering = new ArticleRendering[InteractiveAtom] {
    def html_impl = (atom, data) => interactive.article.html.index(atom, data)
    def css_impl = (atom, data) => interactive.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/interactive/article/index.js")
  }

  val mediaRendering = new ArticleRendering[MediaAtom] {
    def html_impl = (atom, data) => media.article.html.index(atom, data)
    def css_impl = (atom, data) => media.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/media/article/index.js")
  }

  val profileRendering = new ArticleRendering[ProfileAtom] {
    def html_impl = (atom, data) => profile.article.html.index(atom, data)
    def css_impl = (atom, data) => profile.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/profile/article/index.js")
  }

  val qandaRendering = new ArticleRendering[QAndAAtom] {
    def html_impl = (atom, data) => qanda.article.html.index(atom, data)
    def css_impl = (atom, data) => qanda.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/qanda/article/index.js")
  }

  val quizRendering = new ArticleRendering[QuizAtom] {
    def html_impl = (atom, data) => quiz.article.html.index(atom, data)
    def css_impl = (atom, data) => quiz.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/quiz/article/index.js")
  }

  val recipeRendering = new ArticleRendering[RecipeAtom] {
    def html_impl = (atom, data) => recipe.article.html.index(atom, data)
    def css_impl = (atom, data) => recipe.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/recipe/article/index.js")
  }

  val reviewRendering = new ArticleRendering[ReviewAtom] {
    def html_impl = (atom, data) => review.article.html.index(atom, data)
    def css_impl = (atom, data) => review.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/review/article/index.js")
  }

  val storyquestionsRendering = new ArticleRendering[StoryQuestionsAtom] {
    def html_impl = (atom, data) => storyquestions.article.html.index(atom, data)
    def css_impl = (atom, data) => storyquestions.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/storyquestions/article/index.js")
  }

  val timelineRendering = new ArticleRendering[TimelineAtom] {
    def html_impl = (atom, data) => timeline.article.html.index(atom, data)
    def css_impl = (atom, data) => timeline.article.css.index(atom, data)
    def js_impl = (atom, data) => getJavascriptFile("/timeline/article/index.js")
  }
}
