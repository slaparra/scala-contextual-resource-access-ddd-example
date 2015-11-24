import sbt._
import Keys._

object ProjectBuildDefinition extends Build {
  lazy val ScalaTest = "org.scalatest" %% "scalatest" % "2.0" % "test"

  lazy val api = project dependsOn core
  lazy val core = project settings(libraryDependencies ++= Seq(ScalaTest))
}
