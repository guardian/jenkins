package com.gu.contentatom.renderer
package utils
// -----------------------------------------------------------------------------
import cats.Monad
import cats.data.Kleisli
import com.gu.contentapi.client.ContentApiClientLogic
import com.gu.contentatom.thrift.{Atom, AtomType}

import java.io._
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

trait Save[F[_]] {
  def save(filename: String): Kleisli[F, String, Unit]
}

// -----------------------------------------------------------------------------
// Interpreters
class IoCapiRenderer(_apiKey: String, _targetUrl: String = "https://content.guardianapis.com") extends Capi[Task] with CapiRenderer[Task] with Save[Task] {
  import cats.implicits._

  val client = new ContentApiClientLogic { 
    override val apiKey = _apiKey
    override val targetUrl = _targetUrl
  }

  val articleConfig = ArticleConfiguration("http://localhost")
  val emailConfig = EmailConfiguration(
    viewInBrowserUrl = "http://localhost",
    siteUrl = "http://localhost",
    logoUrl = "http://localhost",
    userProfileUrl = "http://localhost",
    unsubscribeUrl = "http://localhost",
    supportTheGuardianUrl = "http://localhost"
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

  def save(filename: String) =
    Kleisli { contents => 
      Task {
        val pw = new PrintWriter(new File(filename))
        pw.write(contents)
        pw.close()
      }
    }

  def getArticle = getAtom.andThen(getHtml(ArticleAtomRenderer)(articleConfig))

  def getEmail = getAtom.andThen(getHtml(EmailAtomRenderer)(emailConfig))

  def getEmailAndSave(filename: String) = getEmail.andThen(save(filename).traverse(_))
}

object IoCapiRenderer {
  def apply(apiKey: String, targetUrl: String) = Task {
    new IoCapiRenderer(apiKey, targetUrl)
  }
}