import sbt._
import Keys._

object ProjectBuildDefinition extends Build {
  lazy val ScalaTest = "org.scalatest" %% "scalatest" % "2.0" % "test"
  lazy val NScalaTime = "com.github.nscala-time" %% "nscala-time" % "2.6.0"

  lazy val api = project dependsOn core
  lazy val core = project settings(libraryDependencies ++= Seq(ScalaTest, NScalaTime))
}
