ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Kafka-Streams"
  )

libraryDependencies ++= Seq(

  // Kafka
  "org.apache.kafka" % "kafka-clients" % "3.4.0",
  "org.apache.kafka" % "kafka-streams" % "3.4.0",
  "org.apache.kafka" %% "kafka-streams-scala" % "3.4.0",

  // Circe
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",

  // JMH
//  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.35",
//
//  // Scala Test
//  "org.scalactic"     %% "scalactic"          % "3.2.15"        % Test,
//  "org.scalatest"     %% "scalatest"          % "3.2.15"        % Test
)
