import sbt._

object Dependencies {
  lazy val circeVersion = "0.8.0"

  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "2.4.55",
    "com.gu"   %% "fezziwig"           % "0.7",
    "io.circe" %% "circe-core"         % circeVersion,
    "io.circe" %% "circe-generic"      % circeVersion,
    "io.circe" %% "circe-parser"       % circeVersion,
    "org.jsoup" % "jsoup"              % "1.11.2"
  )

  val utilsDeps = Seq(
    "com.gu"   %% "content-api-client" % "11.49"
  )
}