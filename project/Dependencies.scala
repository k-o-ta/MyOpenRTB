import sbt._


object Dependencies {
  lazy val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.30"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

  lazy val akkaHttpVersion = "10.1.10"
  lazy val akkaVersion = "2.5.23"
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
}
