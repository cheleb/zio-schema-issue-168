import Dependencies._

ThisBuild / scalaVersion := "2.13.7"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

val zioVersion = "1.0.13"
val zioSchema = "0.1.5"

lazy val root = (project in file("."))
  .settings(
    name := "issue-xxx",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "1.0.13",
      "io.d11" %% "zhttp" % "1.0.0.0-RC18",
      "dev.zio" %% "zio-schema" % zioSchema,
      // Required for automatic generic derivation of schemas
      "dev.zio" %% "zio-schema-derivation" % zioSchema,
      "dev.zio" %% "zio-schema-json" % zioSchema,
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided"
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-test" % zioVersion % "test",
      "dev.zio" %% "zio-test-sbt" % zioVersion % "test",
      "dev.zio" %% "zio-test-magnolia" % zioVersion % "test" // optional
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
