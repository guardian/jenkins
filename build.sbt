import ReleaseTransformations._
import Dependencies._

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

lazy val commonSettings: Seq[Setting[_]] = Metadata.settings ++ Seq(
  scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8", "-language:higherKinds"),
  scalaVersion := scalaVersions.min,
  libraryDependencies ++= coreDeps,
  dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1",
  unmanagedResourceDirectories in Compile += (baseDirectory in ThisBuild).value / "build",
  excludeFilter in Compile in unmanagedResources := "*.fjs" || "*.scss"
)

lazy val coreSettings: Seq[Setting[_]] = Seq(
  name := Metadata.ghProject,
  crossScalaVersions := scalaVersions,
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
  name := Metadata.ghProject + "-email",
  libraryDependencies ++= emailDeps
)

lazy val publishSettings: Seq[Setting[_]] = Seq(
  sonatypeProfileName := "com.gu",
  publishMavenStyle := true
)