organization := "scala-crawl-walk-fly"

name := "default"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
  "com.google.cloud" % "google-cloud-datastore" % "0.5.1"
)
