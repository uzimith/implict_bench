name := "bench"

lazy val root = (project in file(".")).enablePlugins(JmhPlugin)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.7",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.7",
  "org.json4s" %% "json4s-native" % "3.6.2",
  "io.circe" %% "circe-core" % "0.10.0",
  "io.circe" %% "circe-generic" % "0.10.0",
  "io.circe" %% "circe-parser" % "0.10.0"
)
