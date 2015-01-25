name := """archimed-backend"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)

com.github.retronym.sbtxjc.SbtXjcPlugin.xjcSettings

xjcSettings ++ Seq(
  sources in (Compile, xjc) <<= baseDirectory map (_ / "xsd" ** "*.xsd" get),
  sourceManaged in (Compile, xjc) <<= sourceManaged / "main/xsd"
)

