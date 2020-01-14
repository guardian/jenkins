import sbt._

object Dependencies {
  val scalaVersions = Seq("2.11.12", "2.12.10", "2.13.1")
  val http4sVersion = "0.21.0-M5"

  val coreDeps = Seq(
    "com.gu"   %% "content-atom-model" % "3.1.2"
  )

  val emailDeps = Seq(
    "org.jsoup" % "jsoup"              % "1.11.2"
  )

  val utilsDeps = Seq(
    "com.gu"        %% "content-api-client-default" % "15.9",
    "org.http4s"    %% "http4s-dsl"                 % http4sVersion,
    "org.http4s"    %% "http4s-twirl"               % http4sVersion,
    "org.http4s"    %% "http4s-blaze-server"        % http4sVersion,
    "org.http4s"    %% "http4s-blaze-client"        % http4sVersion
  )
}
