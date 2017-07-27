name := "mulefa"
version := "1.0"
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8")
scalaVersion := "2.12.2"
crossScalaVersions := Seq("2.11.11")

libraryDependencies ++= Seq(
  "com.gu" %%  "content-atom-model" % "2.4.42"
)

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value
