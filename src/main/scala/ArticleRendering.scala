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
import com.twitter.scrooge.ThriftStruct
import play.twirl.api.{Html, Css, JavaScript}

abstract class ArticleRendering[A <: ThriftStruct] extends Rendering[A] {
  def html(atom: Atom, data: A) = html_impl(atom, data)
  def css(atom: Atom, data: A) = Some(css_impl(atom, data))
  def js(atom: Atom, data: A) = Some(js_impl(atom, data))

  def html_impl: (Atom, A) => Html
  def css_impl: (Atom, A) => Css
  def js_impl: (Atom, A) => JavaScript
}

object ArticleRendering {
  implicit val ctaRendering = new ArticleRendering[CTAAtom] {
    def html_impl = (atom, data) => cta.article.html.index(atom, data)
    def css_impl = (atom, data) => cta.article.css.index(atom, data)
    def js_impl = (atom, data) => cta.article.js.index(atom, data)
  }

  implicit val explainerRendering = new ArticleRendering[ExplainerAtom] {
    def html_impl = (atom, data) => explainer.article.html.index(atom, data)
    def css_impl = (atom, data) => explainer.article.css.index(atom, data)
    def js_impl = (atom, data) => explainer.article.js.index(atom, data)
  }

  implicit val guideRendering = new ArticleRendering[GuideAtom] {
    def html_impl = (atom, data) => guide.article.html.index(atom, data)
    def css_impl = (atom, data) => guide.article.css.index(atom, data)
    def js_impl = (atom, data) => guide.article.js.index(atom, data)
  }

  implicit val interactiveRendering = new ArticleRendering[InteractiveAtom] {
    def html_impl = (atom, data) => interactive.article.html.index(atom, data)
    def css_impl = (atom, data) => interactive.article.css.index(atom, data)
    def js_impl = (atom, data) => interactive.article.js.index(atom, data)
  }

  implicit val mediaRendering = new ArticleRendering[MediaAtom] {
    def html_impl = (atom, data) => media.article.html.index(atom, data)
    def css_impl = (atom, data) => media.article.css.index(atom, data)
    def js_impl = (atom, data) => media.article.js.index(atom, data)
  }

  implicit val profileRendering = new ArticleRendering[ProfileAtom] {
    def html_impl = (atom, data) => profile.article.html.index(atom, data)
    def css_impl = (atom, data) => profile.article.css.index(atom, data)
    def js_impl = (atom, data) => profile.article.js.index(atom, data)
  }

  implicit val qandaRendering = new ArticleRendering[QAndAAtom] {
    def html_impl = (atom, data) => qanda.article.html.index(atom, data)
    def css_impl = (atom, data) => qanda.article.css.index(atom, data)
    def js_impl = (atom, data) => qanda.article.js.index(atom, data)
  }

  implicit val quizRendering = new ArticleRendering[QuizAtom] {
    def html_impl = (atom, data) => quiz.article.html.index(atom, data)
    def css_impl = (atom, data) => quiz.article.css.index(atom, data)
    def js_impl = (atom, data) => quiz.article.js.index(atom, data)
  }

  implicit val recipeRendering = new ArticleRendering[RecipeAtom] {
    def html_impl = (atom, data) => recipe.article.html.index(atom, data)
    def css_impl = (atom, data) => recipe.article.css.index(atom, data)
    def js_impl = (atom, data) => recipe.article.js.index(atom, data)
  }

  implicit val reviewRendering = new ArticleRendering[ReviewAtom] {
    def html_impl = (atom, data) => review.article.html.index(atom, data)
    def css_impl = (atom, data) => review.article.css.index(atom, data)
    def js_impl = (atom, data) => review.article.js.index(atom, data)
  }

  implicit val storyquestionsRendering = new ArticleRendering[StoryQuestionsAtom] {
    def html_impl = (atom, data) => storyquestions.article.html.index(atom, data)
    def css_impl = (atom, data) => storyquestions.article.css.index(atom, data)
    def js_impl = (atom, data) => storyquestions.article.js.index(atom, data)
  }

  implicit val timelineRendering = new ArticleRendering[TimelineAtom] {
    def html_impl = (atom, data) => timeline.article.html.index(atom, data)
    def css_impl = (atom, data) => timeline.article.css.index(atom, data)
    def js_impl = (atom, data) => timeline.article.js.index(atom, data)
  }
}
