name := "bench"

lazy val root = (project in file(".")).enablePlugins(JmhPlugin)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3"
)
