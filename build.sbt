name := "bookmarks"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.2",
  "ws.securesocial" %% "securesocial" % "2.1.3"
)     

play.Project.playScalaSettings
