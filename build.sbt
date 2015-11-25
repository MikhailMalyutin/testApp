name := "testApp"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  Seq(
    "org.apache.thrift" % "libthrift" % "0.8.0",
    "com.twitter" %% "scrooge-core" % "4.2.0",
    "com.twitter" %% "finagle-thrift" % "6.30.0"
  )
}

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Twitter" at "http://maven.twttr.com"
)

managedSourceDirectories in Compile += file("target/scala-2.11/src_managed")
