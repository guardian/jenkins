import sbt._

object Dependencies {
  lazy val circeVersion = "0.8.0"

  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "2.4.55",
    "org.jsoup" % "jsoup"              % "1.11.2",
    "org.scalacheck" %% "scalacheck"   % "1.13.5" % "test"
  )

  val utilsDeps = Seq(
    "com.gu"   %% "content-api-client" % "11.49",
    "org.typelevel" %% "cats-core"     % "1.0.1",
    "io.monix" %% "monix"              % "3.0.0-M3"
  )
}