name := "scala-3.6-kyo-basics"
version := "1.0"
scalaVersion := "3.6.2"

val kyoVersion = "0.15.1"

libraryDependencies += "io.getkyo" %% "kyo-prelude"       % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-direct"        % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-core"          % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-combinators"   % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-sttp"          % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-tapir"         % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-zio"           % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-caliban"       % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-cache"         % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-stats-otel"    % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-data"          % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-scheduler"     % kyoVersion
libraryDependencies += "io.getkyo" %% "kyo-scheduler-zio" % kyoVersion

scalacOptions ++= Seq(
  "-Wvalue-discard",
  "-Wnonunit-statement",
  "-Wconf:msg=(unused.*value|discarded.*value|pure.*statement):error",
  "-language:strictEquality"
)