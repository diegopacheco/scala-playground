object App extends App{

  import cats.effect._
  import cats.implicits._
  import org.typelevel.vault._

  // Importing global cats-effect runtime to allow .unsafeRunSync();
  // In real code you should follow cats-effect advice on obtaining a runtime
  import cats.effect.unsafe.implicits.global

  case class Bar(a: String, b: Int, c: Long)

  // Creating keys are effects, but interacting with the vault
  // not, it acts like a simple persistent store.
  val basicLookup = for {
    key <- Key.newKey[IO, Bar]
  } yield {
    Vault.empty
      .insert(key, Bar("", 1, 2L))
      .lookup(key)
  }
  print(basicLookup.unsafeRunSync())

}
