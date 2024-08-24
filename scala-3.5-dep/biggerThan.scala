//> using dep com.lihaoyi::os-lib:0.10.3

@main def run(path: String, size: Int) =
  os.list(os.Path(path, os.pwd))
    .filter: p =>
      os.size(p) > size * 1024
    .foreach(println)
