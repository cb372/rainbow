organization := "com.github.cb372"

name := "rainbow"

version := "0.2"

scalaVersion := "2.10.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

scalacOptions ++= Seq("-unchecked", "-feature")

publishTo := Some(Resolver.file("file",  new File( "../cb372.github.com/m2/releases" )) )

