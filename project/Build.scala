import io.gatling.sbt.GatlingPlugin
import sbt.Keys._
import sbt._
import spray.revolver.RevolverPlugin._

object Build extends Build {
  val moduleName = "scala-examples"

  val root = Project(id = moduleName, base = file(".")).enablePlugins(GatlingPlugin)
    .configs(IntegrationTest)
    .settings(Revolver.settings)
    .settings(Defaults.itSettings: _*)
    .settings(
      name := moduleName,
      organization := "com.kissthinker",
      version := "1.0.0-SNAPSHOT",
      scalaVersion := "2.11.7",
      scalacOptions ++= Seq(
        "-feature",
        "-language:implicitConversions",
        "-language:higherKinds",
        "-language:existentials",
        "-language:reflectiveCalls",
        "-language:postfixOps",
        "-Yrangepos",
        "-Yrepl-sync"),
      ivyScala := ivyScala.value map {
        _.copy(overrideScalaVersion = true)
      },
      resolvers ++= Seq(
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
        "Kamon Repository" at "http://repo.kamon.io"),
      libraryDependencies ++= Seq(
        "org.scalactic" %% "scalactic" % "2.2.4" withSources(),
        "org.clapper" %% "grizzled-slf4j" % "1.0.2" withSources(),
        "ch.qos.logback" % "logback-classic" % "1.1.3" withSources()
      ),
      libraryDependencies ++= {
        val specs2Version = "3.6.2"

        Seq(
          "org.mockito" % "mockito-all" % "1.10.19" withSources(),
          "org.specs2" %% "specs2-core" % specs2Version withSources(),
          "org.specs2" %% "specs2-mock" % specs2Version withSources() excludeAll ExclusionRule(organization = "org.mockito"),
          "org.specs2" %% "specs2-matcher-extra" % specs2Version withSources(),
          "org.specs2" %% "specs2-junit" % specs2Version withSources(),
          "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7" % IntegrationTest withSources(),
          "io.gatling" % "gatling-test-framework" % "2.1.7" % IntegrationTest withSources()
        )
      }
    )
    .settings(run := (run in Runtime).evaluated) // Required to stop Gatling plugin overriding the default "run".
}