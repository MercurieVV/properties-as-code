name := "properties-as-code"

version := "0.1"

scalaVersion := "2.12.8"

lazy val scalacheck = "org.scalatest" % "scalatest" % "3.0.5"


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.bintrayRepo("beyondthelines", "maven")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
//libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"
//libraryDependencies += "io.spray" % "spray-json_2.12" % "1.3.5"
libraryDependencies += "io.circe" %% "circe-core" % "0.9.3"
libraryDependencies += "io.circe" %% "circe-generic" % "0.9.3"
libraryDependencies += "io.circe" %% "circe-parser" % "0.9.3"
libraryDependencies += "io.circe" %% "circe-yaml" % "0.6.1"

// for debugging sbt problems
logLevel := Level.Debug