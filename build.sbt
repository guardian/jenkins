name := "atom-renderer"
organization := "com.gu"
version := "0.1-SNAPSHOT"
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8")
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.gu" %%  "content-atom-model" % "2.4.44"
)

TwirlKeys.templateFormats += ("css" -> "jenkins.twirl.CssFormat")

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
resolveFromWebjarsNodeModulesDir := true

resolvers += Resolver.bintrayRepo("webjars","maven")
