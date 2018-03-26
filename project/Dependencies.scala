import sbt._

object Dependencies {
  val scalaVersions = Seq("2.11.11", "2.12.4")
  
  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "2.4.61"
  )

  val emailDeps = Seq(
    "org.jsoup" % "jsoup"              % "1.11.2"
  )

  val utilsDeps = Seq(
    "com.gu"   %% "content-api-client" % "11.53",
    "org.typelevel" %% "cats-core"     % "1.1.0",
    "io.monix" %% "monix"              % "3.0.0-RC1"
  )
}