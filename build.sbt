name := "scala-contextual-resource-access-ddd-example"

version := "1.0"

scalaVersion := "2.11.7"

run in Compile <<= (run in Compile in api)
