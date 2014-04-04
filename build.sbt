import spray.revolver.RevolverPlugin.Revolver

name := "akkaagents"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-agent" % "2.3.1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.1"

libraryDependencies += "com.typesafe.akka" %% "akka-kernel" % "2.3.1"

Revolver.settings