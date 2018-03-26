import ReleaseTransformations._
import Dependencies._

lazy val commonSettings: Seq[Setting[_]] = Seq(
  organization := "com.gu",
  scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8", "-language:higherKinds"),
  scalaVersion := "2.12.4",
  libraryDependencies ++= coreDeps,
  dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1",
  unmanagedResourceDirectories in Compile += (baseDirectory in ThisBuild).value / "build",
  excludeFilter in Compile in unmanagedResources := "*.fjs" || "*.scss"
)

lazy val coreSettings: Seq[Setting[_]] = Seq(
  name := "atom-renderer",
  crossScalaVersions := Seq("2.11.11", "2.12.4"),
  releaseCrossBuild := true,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  ),
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommand("publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  )
)

lazy val emailSettings: Seq[Setting[_]] = Seq(
  name := "atom-renderer-email",
  libraryDependencies ++= emailDeps
)

lazy val publishSettings: Seq[Setting[_]] = Seq(
  sonatypeProfileName := "com.gu",
  publishMavenStyle := true,
  licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/guardian/atom-renderer"),
      "scm:git@github.com:guardian/atom-renderer.git"
    )
  ),
  homepage := scmInfo.value.map(_.browseUrl),
  developers := List(
    Developer(id="regiskuckaertz", name="Regis Kuckaertz", email="regis.kuckaertz@theguardian.com", url=url("https://github.com/regiskuckaertz"))
  )
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    coreSettings,
    publishSettings,
    sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value
  )
  .enablePlugins(SbtTwirl)

lazy val email = (project in file("email"))
  .dependsOn(core)
  .settings(
    commonSettings,
    emailSettings,
    publishSettings,
    sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value
  )
  .enablePlugins(SbtTwirl)

lazy val utils = (project in file("utils"))
  .dependsOn(core, email)
  .settings(
    commonSettings,
    libraryDependencies ++= utilsDeps,
    initialCommands in console := """
      import monix.execution.Scheduler.Implicits.global
      import com.gu.contentatom.thrift._
      import com.gu.contentatom.renderer._
      import com.gu.contentatom.renderer.utils._
    """
  )
