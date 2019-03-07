
name := "scala-futures"

version := "0.1"

scalaVersion := "2.12.8"

val akkaVersion     = "2.5.21"
val akkaHttpVersion = "10.1.7"

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.google.inject" % "guice" % "4.2.2"
  )
}
