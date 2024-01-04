ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Kafka-Streams"
  )

libraryDependencies ++= Seq(

  // JMH
  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.35",
//
//  // Scala Test
//  "org.scalactic"     %% "scalactic"          % "3.2.15"        % Test,
//  "org.scalatest"     %% "scalatest"          % "3.2.15"        % Test
)
