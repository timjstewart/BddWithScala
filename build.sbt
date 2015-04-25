organization := "com.timjstewart"

name := "BddWithScala"

scalaVersion := "2.11.6"

version := "0.1.0-SNAPSHOT"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"

  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "io.spray"            %%  "spray-testkit" % sprayV   % "test",
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV    % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "org.scalatest"       %%  "scalatest"     % "2.2.1"  % "test"

  )
}
