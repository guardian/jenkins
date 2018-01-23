import ReleaseTransformations._
import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.gu",
  scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8"),
  scalaVersion := "2.12.4",
  libraryDependencies ++= coreDeps,
  dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1",
  unmanagedResourceDirectories in Compile += baseDirectory.value / "build",
  excludeFilter in Compile in unmanagedResources := "*.fjs" || "*.scss"
)

lazy val coreSettings = Seq(
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

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    coreSettings,
    sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value
  )
  .enablePlugins(SbtTwirl)

lazy val utils = (project in file("utils"))
  .dependsOn(core)
  .settings(
    commonSettings,
    libraryDependencies ++= utilsDeps
  )



