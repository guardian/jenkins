package com.gu.contentatom.renderer.utils

import com.gu.contentapi.client.{ContentApiClient, GuardianContentClient}
import com.gu.contentapi.client.model.v1.ItemResponse
import com.gu.contentatom.renderer._
import com.gu.contentatom.thrift.AtomType
import cats.data.OptionT
import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._
import org.http4s.syntax._
import org.http4s.twirl._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }
import play.twirl.api.Html


object Main extends IOApp {
  object AtomIdMatcher extends QueryParamDecoderMatcher[String]("id")

  implicit val atomTypeParamDecoder: QueryParamDecoder[AtomType] = new QueryParamDecoder[AtomType] {
    def decode(value: QueryParameterValue) = value.value match {
      case "audio"    => AtomType.Audio.valid
      case "chart"    => AtomType.Chart.valid
      case "guide"    => AtomType.Guide.valid
      case "profile"  => AtomType.Profile.valid
      case "qanda"    => AtomType.Qanda.valid
      case "timeline" => AtomType.Timeline.valid
      case _          => ParseFailure(value.value, "Invalid atom type").invalidNel
    }
  }

  object AtomTypeMatcher extends QueryParamDecoderMatcher[AtomType]("at")

  val config = ArticleConfiguration(
    ajaxUrl = "",
    audioSettings = AudioSettings(false),
    commonsdivisionConfiguration = ArticleConfiguration.CommonsdivisionConfiguration(true)
  )

  val client = new GuardianContentClient(sys.env("CAPI_TEST_KEY"))

  def assetsService(blocker: Blocker): HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req @ GET -> path =>
      StaticFile.fromResource(s"$path", blocker, Some(req)).getOrElseF(NotFound())
  }

  val capiService: HttpRoutes[IO] = HttpRoutes[IO] {
    case GET -> Root :? AtomTypeMatcher(atomType) +& AtomIdMatcher(atomId) =>
      OptionT.liftF(IO.async[ItemResponse] { callback =>
        client.getResponse(ContentApiClient.item(s"atom/${atomType.toString.toLowerCase}/$atomId"))
          .onComplete {
            case Failure(throwable) => callback(Left(throwable))
            case Success(response) => callback(Right(response))
          }
      }.flatMap { resp =>
        atomType match {
          case AtomType.Audio    => 
            resp.audio.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
              Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Audio),
                ArticleAtomRenderer.getJS(AtomType.Audio)
              ))
            )
          case AtomType.Chart    => 
            resp.chart.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
            Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Chart),
                ArticleAtomRenderer.getJS(AtomType.Chart)
              ))
            )
          case AtomType.Guide    => 
            resp.guide.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
              Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Guide),
                ArticleAtomRenderer.getJS(AtomType.Guide)
              ))
            )
          case AtomType.Profile  => 
            resp.profile.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
              Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Profile),
                ArticleAtomRenderer.getJS(AtomType.Profile)
              ))
            )
          case AtomType.Qanda    => 
            resp.qanda.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
              Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Qanda),
                ArticleAtomRenderer.getJS(AtomType.Qanda)
              ))
            )
          case AtomType.Timeline => 
            resp.timeline.fold(NotFound(s"Audio atom with id $atomId could not be found"))(a => 
              Ok(html.page(
                Some(a),
                Some(Html(ArticleAtomRenderer.getHTML(a, config))),
                ArticleAtomRenderer.getCSS(AtomType.Timeline),
                ArticleAtomRenderer.getJS(AtomType.Timeline)
              ))
            )
          case _                 => InternalServerError(s"Hmm, this is weird, it seems ${atomType} hasn't been properly decoded?")
        }        
      })

    case GET -> Root =>
      OptionT.liftF(Ok(html.page(None, None, None, None)))

  }

  def run(args: List[String]): IO[ExitCode] =
    Blocker[IO].use { blocker =>
      BlazeServerBuilder[IO]
        .bindHttp(8080, "localhost")
        .withHttpApp(Router[IO]("/" -> capiService, "/assets" -> assetsService(blocker)).orNotFound)
        .serve
        .compile
        .drain
        .as(ExitCode.Success)
    }
}