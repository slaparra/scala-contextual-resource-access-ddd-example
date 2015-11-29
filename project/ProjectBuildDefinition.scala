import sbt._
import Keys._

object ProjectBuildDefinition extends Build {
  lazy val ScalaTest = "org.scalatest" %% "scalatest" % "2.0" % "test"
  lazy val NScalaTime = "com.github.nscala-time" %% "nscala-time" % "2.6.0"
  def playProject(project: Project) = project settings(
      resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
      libraryDependencies ++= Seq("com.typesafe.play" %% "play" % "2.2.2")
    )

  lazy val api = playProject(project) dependsOn core
  lazy val core = project settings(libraryDependencies ++= Seq(ScalaTest, NScalaTime))
}
