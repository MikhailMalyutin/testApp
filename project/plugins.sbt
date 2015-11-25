logLevel := Level.Warn

resolvers += "bintray" at "https://dl.bintray.com/twittercsl/sbt-plugins/"

resolvers += "Twitter" at "http://maven.twttr.com"

addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "4.1.0")