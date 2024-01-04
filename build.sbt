ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"



lazy val root = (project in file("."))
  .settings(
    name := "JMH"
  )

resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(

  // JMH
  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.35",
  "commons-codec" % "commons-codec" % "20041127.091804",

  // ScalaMeter
  "com.storm-enroute" %% "scalameter" % "0.18" % Test,

// Scala Test
//  "org.scalactic"     %% "scalactic"          % "3.2.15",
  "org.scalatest"     %% "scalatest"          % "3.2.15"        % Test
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
parallelExecution in Test := false