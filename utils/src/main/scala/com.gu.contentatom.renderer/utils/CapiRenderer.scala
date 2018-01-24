package com.gu.contentatom.renderer
package utils
// -----------------------------------------------------------------------------
import cats.Monad
import cats.data.Kleisli

import com.gu.contentapi.client.GuardianContentClient
import com.gu.contentatom.thrift.{Atom, AtomType}

import monix.execution.Scheduler.Implicits.global
import monix.eval.Task
// -----------------------------------------------------------------------------
// Algebras
trait Capi[F[_]] {
  def getAtom: Kleisli[F, (String, AtomType), Option[Atom]]
}

trait CapiRenderer[F[_]] {
  def getHtml(renderer: AtomRenderer)(config: renderer.Conf): Kleisli[F, Option[Atom], Option[String]]
}

// -----------------------------------------------------------------------------
// Interpreters
class IoCapiRenderer(apiKey: String) extends Capi[Task] with CapiRenderer[Task] {
  import cats.implicits._

  val client = new GuardianContentClient(apiKey)

  val articleConfig = ArticleConfiguration("http://localhost")
  val emailConfig = EmailConfiguration(
    "http://localhost",
    "http://localhost",
    "http://localhost",
    "http://localhost",
    "http://localhost"
  )

  def getAtom = Kleisli { case ((id, typ)) =>
    Task.fromFuture {
      val path = s"atom/${typ.name.toLowerCase}/$id"
      println(s"Fetching $path")
      client.getResponse(client.item(path))
    }.map { response =>
      typ match {
        case AtomType.Guide    => response.guide
        case AtomType.Profile  => response.profile
        case AtomType.Qanda    => response.qanda
        case AtomType.Timeline => response.timeline
        case AtomType.Storyquestions => response.storyquestions
        case AtomType.Explainer=> response.explainer
        case _                 => None
      }
    }
  }
  
  def getHtml(renderer: AtomRenderer)(config: renderer.Conf) = 
    Kleisli { optAtom => Task.now(optAtom.map(renderer.getHTML(_, config))) }

  def getArticle = getAtom.andThen(getHtml(ArticleAtomRenderer)(articleConfig))

  def getEmail = getAtom.andThen(getHtml(EmailAtomRenderer)(emailConfig))
}

object IoCapiRenderer {
  def apply(apiKey: String) = Task {
    new IoCapiRenderer(apiKey)
  }
}