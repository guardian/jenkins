package com.gu.contentatom.renderer
package renderers

import com.gu.contentatom.thrift.Atom
import com.gu.contentatom.thrift.atom.commonsdivision.CommonsDivision
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
import utils.LoadFromClasspath

trait ArticleRendering[A] extends Rendering[A] {
  def html(atom: Atom, data: A) = html_impl(atom, data)
  def css = css_impl()
  def js = js_impl()

  def html_impl: (Atom, A) => Html
  def css_impl: () => Option[String]
  def js_impl: () => Option[String]
}

object ArticleRenderings extends Renderings {
  val ctaRendering = new ArticleRendering[CTAAtom] {
    val html_impl = (atom, data) => cta.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/cta/article/index.css")
    val js_impl = () => LoadFromClasspath("/cta/article/index.js")
  }

  val explainerRendering = new ArticleRendering[ExplainerAtom] {
    val html_impl = (atom, data) => explainer.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/explainer/article/index.css")
    val js_impl = () => LoadFromClasspath("/explainer/article/index.js")
  }

  val guideRendering = new ArticleRendering[GuideAtom] {
    val html_impl = (atom, data) => guide.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/guide/article/index.css")
    val js_impl = () => LoadFromClasspath("/guide/article/index.js")
  }

  val interactiveRendering = new ArticleRendering[InteractiveAtom] {
    val html_impl = (atom, data) => interactive.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/interactive/article/index.css")
    val js_impl = () => LoadFromClasspath("/interactive/article/index.js")
  }

  val mediaRendering = new ArticleRendering[MediaAtom] {
    val html_impl = (atom, data) => media.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/media/article/index.css")
    val js_impl = () => LoadFromClasspath("/media/article/index.js")
  }

  val profileRendering = new ArticleRendering[ProfileAtom] {
    val html_impl = (atom, data) => profile.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/profile/article/index.css")
    val js_impl = () => LoadFromClasspath("/profile/article/index.js")
  }

  val qandaRendering = new ArticleRendering[QAndAAtom] {
    val html_impl = (atom, data) => qanda.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/qanda/article/index.css")
    val js_impl = () => LoadFromClasspath("/qanda/article/index.js")
  }

  val quizRendering = new ArticleRendering[QuizAtom] {
    val html_impl = (atom, data) => quiz.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/quiz/article/index.css")
    val js_impl = () => LoadFromClasspath("/quiz/article/index.js")
  }

  val recipeRendering = new ArticleRendering[RecipeAtom] {
    val html_impl = (atom, data) => recipe.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/recipe/article/index.css")
    val js_impl = () => LoadFromClasspath("/recipe/article/index.js")
  }

  val reviewRendering = new ArticleRendering[ReviewAtom] {
    val html_impl = (atom, data) => review.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/review/article/index.css")
    val js_impl = () => LoadFromClasspath("/review/article/index.js")
  }

  val storyquestionsRendering = new ArticleRendering[StoryQuestionsAtom] {
    val html_impl = (atom, data) => storyquestions.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/storyquestions/article/index.css")
    val js_impl = () => LoadFromClasspath("/storyquestions/article/index.js")
  }

  val timelineRendering = new ArticleRendering[TimelineAtom] {
    val html_impl = (atom, data) => timeline.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/timeline/article/index.css")
    val js_impl = () => LoadFromClasspath("/timeline/article/index.js")
  }

  val commonsdivisionRendering = new ArticleRendering[CommonsDivision] {
    val html_impl = (atom, data) => commonsdivision.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/commonsdivision/article/index.css")
    val js_impl = () => LoadFromClasspath("/commonsdivision/article/index.js")
  }
}
