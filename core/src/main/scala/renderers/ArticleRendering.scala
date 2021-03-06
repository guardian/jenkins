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
import com.gu.contentatom.thrift.atom.timeline.TimelineAtom
import com.gu.contentatom.thrift.atom.chart.ChartAtom
import com.gu.contentatom.thrift.atom.audio.AudioAtom

import play.twirl.api.Html
import utils.LoadFromClasspath

trait ArticleRendering[A] extends Rendering[A] {
  type Conf = ArticleConfiguration

  def css = css_impl()
  def js = js_impl()

  def css_impl: () => Option[String]
  def js_impl: () => Option[String]
}

object ArticleRenderings extends Renderings {
  val ctaRendering = new ArticleRendering[CTAAtom] {
    def html[C <: Conf](atom: Atom, data: CTAAtom)(implicit conf: C) = 
      cta.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/cta/article/index.css")
    val js_impl = () => LoadFromClasspath("/cta/article/index.js")
  }

  val explainerRendering = new ArticleRendering[ExplainerAtom] {
    def html[C <: Conf](atom: Atom, data: ExplainerAtom)(implicit conf: C) = 
      explainer.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/explainer/article/index.css")
    val js_impl = () => LoadFromClasspath("/explainer/article/index.js")
  }

  val guideRendering = new ArticleRendering[GuideAtom] {
    def html[C <: Conf](atom: Atom, data: GuideAtom)(implicit conf: C) = 
      guide.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/guide/article/index.css")
    val js_impl = () => LoadFromClasspath("/guide/article/index.js")
  }

  val interactiveRendering = new ArticleRendering[InteractiveAtom] {
    def html[C <: Conf](atom: Atom, data: InteractiveAtom)(implicit conf: C) = 
      interactive.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/interactive/article/index.css")
    val js_impl = () => LoadFromClasspath("/interactive/article/index.js")
  }

  val mediaRendering = new ArticleRendering[MediaAtom] {
    def html[C <: Conf](atom: Atom, data: MediaAtom)(implicit conf: C) = 
      media.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/media/article/index.css")
    val js_impl = () => LoadFromClasspath("/media/article/index.js")
  }

  val profileRendering = new ArticleRendering[ProfileAtom] {
    def html[C <: Conf](atom: Atom, data: ProfileAtom)(implicit conf: C) = 
      profile.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/profile/article/index.css")
    val js_impl = () => LoadFromClasspath("/profile/article/index.js")
  }

  val qandaRendering = new ArticleRendering[QAndAAtom] {
    def html[C <: Conf](atom: Atom, data: QAndAAtom)(implicit conf: C) = 
      qanda.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/qanda/article/index.css")
    val js_impl = () => LoadFromClasspath("/qanda/article/index.js")
  }

  val quizRendering = new ArticleRendering[QuizAtom] {
    def html[C <: Conf](atom: Atom, data: QuizAtom)(implicit conf: C) = 
      quiz.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/quiz/article/index.css")
    val js_impl = () => LoadFromClasspath("/quiz/article/index.js")
  }

  val recipeRendering = new ArticleRendering[RecipeAtom] {
    def html[C <: Conf](atom: Atom, data: RecipeAtom)(implicit conf: C) = 
      recipe.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/recipe/article/index.css")
    val js_impl = () => LoadFromClasspath("/recipe/article/index.js")
  }

  val reviewRendering = new ArticleRendering[ReviewAtom] {
    def html[C <: Conf](atom: Atom, data: ReviewAtom)(implicit conf: C) = 
      review.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/review/article/index.css")
    val js_impl = () => LoadFromClasspath("/review/article/index.js")
  }

  val timelineRendering = new ArticleRendering[TimelineAtom] {
    def html[C <: Conf](atom: Atom, data: TimelineAtom)(implicit conf: C) = 
      timeline.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/timeline/article/index.css")
    val js_impl = () => LoadFromClasspath("/timeline/article/index.js")
  }

  val commonsdivisionRendering = new ArticleRendering[CommonsDivision] {
    def html[C <: Conf](atom: Atom, data: CommonsDivision)(implicit conf: C) =
      commonsdivision.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/commonsdivision/article/index.css")
    val js_impl = () => LoadFromClasspath("/commonsdivision/article/index.js")
  }

  val chartRendering = new ArticleRendering[ChartAtom] {
    def html[C <: Conf](atom: Atom, data: ChartAtom)(implicit conf: C) =
      chart.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/chart/article/index.css")
    val js_impl = () => LoadFromClasspath("/chart/article/index.js")
  }

  val audioRendering = new ArticleRendering[AudioAtom] {
    def html[C <: Conf](atom: Atom, data: AudioAtom)(implicit conf: C) =
      audio.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/audio/article/index.css")
    val js_impl = () => LoadFromClasspath("/audio/article/index.js")
  }
}
