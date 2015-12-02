import sbt._
import Keys._
import play.sbt._

object ProjectBuildDefinition extends Build {
  lazy val ScalaTest = "org.scalatest" %% "scalatest" % "2.0" % "test"
  lazy val NScalaTime = "com.github.nscala-time" %% "nscala-time" % "2.6.0"
  lazy val Scallop = "org.rogach" %% "scallop" % "0.9.5"

  lazy val api = project enablePlugins PlayScala dependsOn core
  lazy val cli = project settings(
      libraryDependencies ++= Seq(Scallop),
      mainClass in Compile := Some("kmruiz.cli.CLI")
    ) dependsOn core
  lazy val core = project settings(libraryDependencies ++= Seq(ScalaTest, NScalaTime))
}
