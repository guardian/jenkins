import sbt.Keys._
import ReleaseTransformations._
import Dependencies._

lazy val root = (project in file("."))
  .aggregate(core, email, utils)
  .settings(commonSettings, disablePublishingSettings)
  .settings(
    Compile / sources := Seq.empty,
    Test    / sources := Seq.empty,
    releaseVcsSign    := true,
    releaseCrossBuild := true,
    releaseProcess    := releaseSteps
  )

lazy val core = (project in file("core"))
  .settings(commonSettings, coreSettings, publishSettings, twirlSettings)
  .enablePlugins(SbtTwirl, SbtWeb)

lazy val email = (project in file("email"))
  .dependsOn(core)
  .settings(commonSettings, emailSettings, publishSettings, twirlSettings)
  .enablePlugins(SbtTwirl)

lazy val utils = (project in file("utils"))
  .dependsOn(core, email)
  .settings(commonSettings, utilsSettings, disablePublishingSettings)

lazy val commonSettings: Seq[Setting[_]] = Metadata.settings ++ 
  Seq ( crossScalaVersions  := scalaVersions
      , scalacOptions       ++= Seq("-feature", "-deprecation", "-target:jvm-1.8", "-language:higherKinds")
      , scalaVersion        := scalaVersions.min
      , libraryDependencies ++= coreDeps
      , dependencyOverrides += "org.apache.thrift" % "libthrift" % "0.9.1"
      , publishTo           := sonatypePublishTo.value
      , Compile / unmanagedResourceDirectories       += (ThisBuild / baseDirectory).value / "build"
      , Compile / unmanagedResources / excludeFilter := "*.fjs" || "*.scss"
      )

lazy val coreSettings: Seq[Setting[_]] = 
  Seq ( name                         := Metadata.ghProject
      , WebKeys.pipeline             := WebKeys.pipeline.dependsOn(webpack.toTask("")).value
      , webpack / WebpackKeys.config := file("apps.config.js")
      )

lazy val emailSettings: Seq[Setting[_]] = 
  Seq ( name := Metadata.ghProject + "-email"
      , libraryDependencies ++= emailDeps
      )

lazy val utilsSettings: Seq[Setting[_]] =
  Seq ( libraryDependencies ++= utilsDeps
      , console / initialCommands := 
        """import monix.execution.Scheduler.Implicits.global
          |import com.gu.contentatom.thrift._
          |import com.gu.contentatom.renderer._
          |import com.gu.contentatom.renderer.utils._
        """.stripMargin
      )


lazy val twirlSettings: Seq[Setting[_]] = 
  Seq ( Compile / TwirlKeys.compileTemplates / sourceDirectories  += (Compile / resourceDirectory).value
      )

lazy val publishSettings: Seq[Setting[_]] = 
  Seq ( sonatypeProfileName := "com.gu"
      , publishMavenStyle := true
      )

lazy val releasePublishAction: TaskKey[_] = PgpKeys.publishSigned
lazy val releaseSteps: Seq[ReleaseStep] = 
  Seq ( runClean
      , checkSnapshotDependencies
      , inquireVersions
      , runTest
      , setReleaseVersion
      , commitReleaseVersion
      , tagRelease
      , releaseStepCommandAndRemaining(s"+${releasePublishAction.key.label}")
      , setNextVersion
      , commitNextVersion
      , pushChanges
      )

val disablePublishingSettings: Seq[Setting[_]] = 
  Seq (publish / skip := true)