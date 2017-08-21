package jenkins

import com.gu.contentatom.thrift.Atom
import java.io.InputStream
import play.twirl.api.{Html, JavaScript}
import scala.io.Source
import twirl.Css

trait Rendering[A] {
  def html: (Atom, A) => Html
  def css: (Atom, A) => Option[Css]
  def js: (Atom, A) => Option[String]

  protected def getJavascriptFile(path: String): Option[String] =
    Option(getClass.getResourceAsStream(path))
      .map(is => Source.fromInputStream(is).mkString)
}
