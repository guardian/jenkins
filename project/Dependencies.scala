import sbt._

object Dependencies {
  val scalaVersions = Seq("2.11.12", "2.12.8")
  
  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "2.4.66"
  )

  val emailDeps = Seq(
    "org.jsoup" % "jsoup"              % "1.11.2"
  )

  val utilsDeps = Seq(
    "com.gu"   %% "content-api-client-default" % "12.14",
    "org.typelevel" %% "cats-core"     % "1.1.0",
    "io.monix" %% "monix"              % "3.0.0-RC1"
  )
}
