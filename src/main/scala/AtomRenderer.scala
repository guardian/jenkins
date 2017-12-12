package com.gu.contentatom.renderer

import com.gu.contentatom.thrift.{Atom, AtomData, AtomType}
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
import io.circe._
import io.circe.parser._
import renderers.{Renderings, Rendering}

trait AtomRenderer {
  protected val renderings: Renderings
  import renderings._
  import json._

  type Conf <: Configuration

  type HTML = String
  type CSS = Option[String]
  type JS = Option[String]

  def getHTML[A](atom: Atom, data: A, conf: Conf)(implicit reader: Rendering[A]): HTML =
    reader.html(atom, data)(conf.asInstanceOf[reader.Conf]).toString
  
  def getHTML(atom: Atom, conf: Conf): HTML = atom.data match {
    case AtomData.Cta(data)            => getHTML(atom, data, conf)
    case AtomData.Explainer(data)      => getHTML(atom, data, conf)
    case AtomData.Guide(data)          => getHTML(atom, data, conf)
    case AtomData.Interactive(data)    => getHTML(atom, data, conf)
    case AtomData.Media(data)          => getHTML(atom, data, conf)
    case AtomData.Profile (data)       => getHTML(atom, data, conf)
    case AtomData.Qanda(data)          => getHTML(atom, data, conf)
    case AtomData.Quiz(data)           => getHTML(atom, data, conf)
    case AtomData.Recipe(data)         => getHTML(atom, data, conf)
    case AtomData.Review(data)         => getHTML(atom, data, conf)
    case AtomData.Storyquestions(data) => getHTML(atom, data, conf)
    case AtomData.Timeline(data)       => getHTML(atom, data, conf)
    case _                             => atom.defaultHtml
  }

  def getHTML(json: Json, conf: Conf): Option[HTML] = json.as[Atom] match {
    case Left(_) => None
    case Right(atom) => Some(getHTML(atom, conf))
  }

  def getHTML(json: String, conf: Conf): Option[HTML] =
    parse(json).right.toOption.flatMap(getHTML(_, conf))

  def getCSS[A](implicit reader: Rendering[A]): CSS =
    reader.css.map(_.toString)

  def getJS[A](implicit reader: Rendering[A]): JS =
    reader.js.map(_.toString)
  
  def getCSS(atomType: AtomType): CSS = atomType match {
    case AtomType.Cta            => getCSS[CTAAtom]
    case AtomType.Explainer      => getCSS[ExplainerAtom]
    case AtomType.Guide          => getCSS[GuideAtom]
    case AtomType.Interactive    => getCSS[InteractiveAtom]
    case AtomType.Media          => getCSS[MediaAtom]
    case AtomType.Profile        => getCSS[ProfileAtom]
    case AtomType.Qanda          => getCSS[QAndAAtom]
    case AtomType.Quiz           => getCSS[QuizAtom]
    case AtomType.Recipe         => getCSS[RecipeAtom]
    case AtomType.Review         => getCSS[ReviewAtom]
    case AtomType.Storyquestions => getCSS[StoryQuestionsAtom]
    case AtomType.Timeline       => getCSS[TimelineAtom]
    case _                       => None
  }

  def getCSS(atomTypes: Seq[AtomType]): Seq[String] =
    atomTypes.distinct.map(getCSS).flatten
  
  def getJS(atomType: AtomType): JS = atomType match {
    case AtomType.Cta            => getJS[CTAAtom]
    case AtomType.Explainer      => getJS[ExplainerAtom]
    case AtomType.Guide          => getJS[GuideAtom]
    case AtomType.Interactive    => getJS[InteractiveAtom]
    case AtomType.Media          => getJS[MediaAtom]
    case AtomType.Profile        => getJS[ProfileAtom]
    case AtomType.Qanda          => getJS[QAndAAtom]
    case AtomType.Quiz           => getJS[QuizAtom]
    case AtomType.Recipe         => getJS[RecipeAtom]
    case AtomType.Review         => getJS[ReviewAtom]
    case AtomType.Storyquestions => getJS[StoryQuestionsAtom]
    case AtomType.Timeline       => getJS[TimelineAtom]
    case _                       => None
  }

  def getJS(atomTypes: Seq[AtomType]): Seq[String] =
    atomTypes.distinct.map(getJS).flatten
}

object ArticleAtomRenderer extends AtomRenderer {
  type Conf = NilConfiguration
  val renderings = renderers.ArticleRenderings

  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(atom: Atom): HTML = getHTML(atom, NilConfiguration)
  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(json: Json): Option[HTML] = getHTML(json, NilConfiguration)
  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(json: String): Option[HTML] = getHTML(json, NilConfiguration)
}

object DefaultAtomRenderer extends AtomRenderer {
  type Conf = NilConfiguration
  val renderings = renderers.DefaultRenderings

  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(atom: Atom): HTML = getHTML(atom, NilConfiguration)
  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(json: Json): Option[HTML] = getHTML(json, NilConfiguration)
  @deprecated("will be removed", "atom-renderer 0.10")
  def getHTML(json: String): Option[HTML] = getHTML(json, NilConfiguration)
}
