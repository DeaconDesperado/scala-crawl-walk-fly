organization := "scala-crawl-walk-fly"

name := "default"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11", 
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.11", 
  "com.google.cloud" % "google-cloud-datastore" % "0.5.1"
)
