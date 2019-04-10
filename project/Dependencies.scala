import sbt._

object Dependencies {
  val scalaVersions = Seq("2.11.12", "2.12.8")
  val http4sVersion = "0.20.0-M5"

  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "3.0.0"
  )

  val emailDeps = Seq(
    "org.jsoup" % "jsoup"              % "1.11.2"
  )

  val utilsDeps = Seq(
    "com.gu"        %% "content-api-client-default" % "13.0",
    "org.http4s"    %% "http4s-dsl"                 % http4sVersion,
    "org.http4s"    %% "http4s-twirl"               % http4sVersion,
    "org.http4s"    %% "http4s-blaze-server"        % http4sVersion,
    "org.http4s"    %% "http4s-blaze-client"        % http4sVersion
  )
}
