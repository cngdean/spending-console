name := """spending-console"""

version := "1.0"

scalaVersion := "2.11.1"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

libraryDependencies += "net.sf.ofx4j" % "ofx4j" % "1.6"

libraryDependencies += "org.jfree" % "jfreechart" % "1.0.19"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3"

