package jenkins

import com.gu.contentatom.thrift.Atom
import java.io.InputStream
import play.twirl.api.{Html, JavaScript}
import scala.io.Source
import twirl.Css

trait Rendering[A] {
  def html(atom: Atom, data: A): Html
  def css: Option[Css]
  def js: Option[String]

  protected def getJavascriptFile(path: String): Option[String] =
    Option(getClass.getResourceAsStream(path))
      .map(is => Source.fromInputStream(is).mkString)
}
