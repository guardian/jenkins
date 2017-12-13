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
  type Conf = EmailConfiguration

  def css = css_impl()
  def js = None

  def css_impl: () => Option[String]
}

object EmailRenderings extends Renderings {
  val ctaRendering = new EmailRendering[CTAAtom] {
    def html[C <: Conf](atom: Atom, data: CTAAtom)(implicit conf: C) =
      cta.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/cta/email/index.css")
  }

  val explainerRendering = new EmailRendering[ExplainerAtom] {
    def html[C <: Conf](atom: Atom, data: ExplainerAtom)(implicit conf: C) =
      explainer.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/explainer/email/index.css")
  }

  val guideRendering = new EmailRendering[GuideAtom] {
    def html[C <: Conf](atom: Atom, data: GuideAtom)(implicit conf: C) =
      guide.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/guide/email/index.css")
  }

  val interactiveRendering = new EmailRendering[InteractiveAtom] {
    def html[C <: Conf](atom: Atom, data: InteractiveAtom)(implicit conf: C) =
      interactive.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/interactive/email/index.css")
  }

  val mediaRendering = new EmailRendering[MediaAtom] {
    def html[C <: Conf](atom: Atom, data: MediaAtom)(implicit conf: C) =
      media.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/media/email/index.css")
  }

  val profileRendering = new EmailRendering[ProfileAtom] {
    def html[C <: Conf](atom: Atom, data: ProfileAtom)(implicit conf: C) =
      profile.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/profile/email/index.css")
  }

  val qandaRendering = new EmailRendering[QAndAAtom] {
    def html[C <: Conf](atom: Atom, data: QAndAAtom)(implicit conf: C) =
      qanda.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/qanda/email/index.css")
  }

  val quizRendering = new EmailRendering[QuizAtom] {
    def html[C <: Conf](atom: Atom, data: QuizAtom)(implicit conf: C) =
      quiz.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/quiz/email/index.css")
  }

  val recipeRendering = new EmailRendering[RecipeAtom] {
    def html[C <: Conf](atom: Atom, data: RecipeAtom)(implicit conf: C) =
      recipe.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/recipe/email/index.css")
  }

  val reviewRendering = new EmailRendering[ReviewAtom] {
    def html[C <: Conf](atom: Atom, data: ReviewAtom)(implicit conf: C) =
      review.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/review/email/index.css")
  }

  val storyquestionsRendering = new EmailRendering[StoryQuestionsAtom] {
    def html[C <: Conf](atom: Atom, data: StoryQuestionsAtom)(implicit conf: C) =
      storyquestions.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/storyquestions/email/index.css")
  }

  val timelineRendering = new EmailRendering[TimelineAtom] {
    def html[C <: Conf](atom: Atom, data: TimelineAtom)(implicit conf: C) =
      timeline.email.html.index(atom, data)
    val css_impl = () => LoadFromClasspath("/timeline/email/index.css")
  }
}
    
