import sbt._
import sbt.Keys._

object Metadata {
  val ghUser = "guardian"
  val ghProject = "atom-renderer"

  lazy val settings = Seq(
    organization := "com.gu",
    organizationName := "Guardian News & Media Ltd",
    organizationHomepage := Some(url("https://www.theguardian.com/")),

    startYear := Some(2017),
    licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),

    scmInfo := Some(ScmInfo(
      url(s"https://github.com/$ghUser/$ghProject"),
      s"scm:git@github.com:$ghUser/$ghProject.git"
    )),

    homepage := scmInfo.value.map(_.browseUrl),

    developers := List(
      Developer(id="regiskuckaertz", name="Regis Kuckaertz", email="regis.kuckaertz@theguardian.com", url=url("https://github.com/regiskuckaertz"))
    )
  )
}