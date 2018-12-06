name := "bench"

lazy val root = (project in file(".")).enablePlugins(JmhPlugin)

scalaVersion := "2.12.8"
