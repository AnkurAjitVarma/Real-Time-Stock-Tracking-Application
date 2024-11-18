lazy val akkaHttpVersion = "10.6.1"

lazy val akkaVersion = "2.9.2"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val root = (project in file("."))
  .settings(
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.15",
    name := "Real-Time-Stock-Tracking-Application",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "7.0.2",
      "org.ta4j" % "ta4j-core" % "0.16" exclude("org.slf4j", "slf4j-api"),
      "ch.qos.logback" % "logback-classic" % "1.5.6",

      // Test dependencies
      "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ),
    assembly/assemblyMergeStrategy := {
      case PathList("module-info.class") => MergeStrategy.last
      case x =>
        val oldStrategy = (assembly/assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )
  .enablePlugins(AssemblyPlugin)
