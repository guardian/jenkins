name := "mulefa"
version := "1.0"
scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.8")
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.gu"        %  "content-atom-model_2.12" % "2.4.42"
)

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (resourceDirectory in Compile).value
