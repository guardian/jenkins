import ReleaseTransformations._

name := "atom-renderer"
organization := "com.gu"
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8")
scalaVersion := "2.12.4"
crossScalaVersions := Seq("2.11.11", "2.12.4")
releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value

libraryDependencies ++= Seq(
  "com.gu"   %% "content-atom-model" % "2.4.55"
)

/**
 * WARNING - upgrading the following will break clients
 */
dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1"

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value

// Add CSS and JS generated files
unmanagedResourceDirectories in Compile += baseDirectory.value / "build"

// Ignore JS and SASS files
excludeFilter in Compile in unmanagedResources := "*.fjs" || "*.scss"

// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

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