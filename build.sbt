val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "web3j-rlp-playground",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    // libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.12" % Test,
      "org.web3j" % "core" % "4.8.7",
    ),
  )
