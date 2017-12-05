package com.gu.contentatom.renderer
package renderers

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

import utils.LoadFromClasspath

trait EmailRendering[A] extends Rendering[A] {
  def html(atom: Atom, data: A) = html_impl(atom, data)
  def css = css_impl()
  def js = None

  def html_impl: (Atom, A) => Html
  def css_impl: () => Option[String]
}

object EmailRenderings extends Renderings {
  val ctaRendering = new EmailRendering[CTAAtom] {
    val html_impl = (atom, data) => cta.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/cta/email/index.css")
  }

  val explainerRendering = new EmailRendering[ExplainerAtom] {
    val html_impl = (atom, data) => explainer.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/explainer/email/index.css")
  }

  val guideRendering = new EmailRendering[GuideAtom] {
    val html_impl = (atom, data) => guide.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/guide/email/index.css")
  }

  val interactiveRendering = new EmailRendering[InteractiveAtom] {
    val html_impl = (atom, data) => interactive.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/interactive/email/index.css")
  }

  val mediaRendering = new EmailRendering[MediaAtom] {
    val html_impl = (atom, data) => media.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/media/email/index.css")
  }

  val profileRendering = new EmailRendering[ProfileAtom] {
    val html_impl = (atom, data) => profile.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/profile/email/index.css")
  }

  val qandaRendering = new EmailRendering[QAndAAtom] {
    val html_impl = (atom, data) => qanda.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/qanda/email/index.css")
  }

  val quizRendering = new EmailRendering[QuizAtom] {
    val html_impl = (atom, data) => quiz.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/quiz/email/index.css")
  }

  val recipeRendering = new EmailRendering[RecipeAtom] {
    val html_impl = (atom, data) => recipe.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/recipe/email/index.css")
  }

  val reviewRendering = new EmailRendering[ReviewAtom] {
    val html_impl = (atom, data) => review.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/review/email/index.css")
  }

  val storyquestionsRendering = new EmailRendering[StoryQuestionsAtom] {
    val html_impl = (atom, data) => storyquestions.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/storyquestions/email/index.css")
  }

  val timelineRendering = new EmailRendering[TimelineAtom] {
    val html_impl = (atom, data) => timeline.article.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/timeline/email/index.css")
  }
}
