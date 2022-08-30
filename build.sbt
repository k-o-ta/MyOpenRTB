import Dependencies._

ThisBuild / scalaVersion := "2.10.7"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixOnCompile := true

lazy val akkaHttpVersion = "10.1.10"
lazy val akkaVersion = "2.5.23"

lazy val root = (project in file("."))
  .settings(
    name := "MyOpenRTB",
    scalacOptions += "-Ywarn-unused-import",
    libraryDependencies ++= Seq(
      scalaz,
      scalaTest % Test,
      akkaHttp,
      akkaHttpSprayJson,
      //      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      akkaStream,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      akkaHttpTestKit,
      //      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
      "org.scalatest" %% "scalatest" % "3.1.4" % Test
    )
  )
lazy val core = (project in file("core"))
  .settings(
    name := "MyOpenRTBCore",
    scalacOptions += "-Ywarn-unused-import",
    libraryDependencies ++= Seq(
      scalaz,
      scalaTest % Test,
      akkaHttp,
      akkaHttpSprayJson,
      //      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      akkaStream,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      akkaHttpTestKit,
      //      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
      "org.scalatest" %% "scalatest" % "3.1.4" % Test
    )
  )
javaOptions in reStart += "-Xdebug"
//javaOptions in reStart += "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5050"
debugSettings := Some(new spray.revolver.DebugSettings(port = 5005, suspend = true))
//Fork.java.fork(null, null)
//reStartArgs := Seq("")
//javaOptions := Seq(
//  "-Xdebug",
//  "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5050"
//)
//Revolver.enableDebugging(port = 5005, suspend = true)
//javaOptions := Seq(
//  "-Xdebug",
//  "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
//)
// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
