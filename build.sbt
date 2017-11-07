import ReleaseTransformations._

name := "atom-renderer"
organization := "com.gu"
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8")
scalaVersion := "2.11.11"

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "com.gu"   %% "content-atom-model" % "2.4.51",
  "com.gu"   %% "fezziwig"           % "0.4",
  "io.circe" %% "circe-core"         % circeVersion,
  "io.circe" %% "circe-generic"      % circeVersion,
  "io.circe" %% "circe-parser"       % circeVersion
)

/**
 * WARNING - upgrading the following will break clients
 */
dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1"

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
resolveFromWebjarsNodeModulesDir := true

resolvers += Resolver.bintrayRepo("webjars","maven")

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