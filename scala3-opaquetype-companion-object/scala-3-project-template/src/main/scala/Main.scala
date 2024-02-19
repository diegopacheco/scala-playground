/**
 * Opaque type only exists on the scope of PII object
 * The association from Name == String and Email == String also only happens
 *  inside the scope of PII object.
 */ 
object PII{

  import java.security.MessageDigest
  import java.util
  import javax.crypto.Cipher
  import javax.crypto.KeyGenerator
  import javax.crypto.spec.SecretKeySpec
  import org.apache.commons.codec.binary.Base64

  opaque type Name    = String
  opaque type Email   = String

  object Name {
    def fromString(s: String): Option[Name] = Some(encrypt(s))
    def fromName(s: Name): Option[Name] = Some(decrypt(s))
  }

  object Email {
    def fromString(s: String): Option[Email] = Some(encrypt(s))
    def fromEmail(s: Email): Option[Email] = Some(decrypt(s))
  }

  // 
  // Attention kids - careful with this!  
  //  
  // this is only to show an idea with scala opaque types and object companion
  // dont do this at home kids - dont store secrets in code/gh.
  // this should be externalized to a proper KMS system.
  // also if you restart this code wont work with previous ciphers since the key keeps changing.
  // again is just a poc for scala - not ideal secure system
  //
  private val SALT  : String = "fwer23454h61d2d2wfg43$#TG$7j56d12bv$%#G%$$%^JHf2$#$$"
  private val KEY   :  String = getKey()
  private val AES_OP: String = "AES/ECB/PKCS5Padding"

  private def getKey():String = {
    val keyGen:KeyGenerator = KeyGenerator.getInstance("AES")
    keyGen.init(256)
    keyGen.generateKey().getEncoded().toString
  }  

  private def encrypt(s:String):String = {
    val cipher: Cipher = Cipher.getInstance(AES_OP)
    cipher.init(Cipher.ENCRYPT_MODE, keyToSpec(KEY))
    Base64.encodeBase64String(cipher.doFinal(s.getBytes("UTF-8")))
  }

  private def decrypt(cipherText:String): String = {
    val cipher: Cipher = Cipher.getInstance(AES_OP)
    cipher.init(Cipher.DECRYPT_MODE, keyToSpec(KEY))
    new String(cipher.doFinal(Base64.decodeBase64(cipherText)))
  }

  def keyToSpec(key: String): SecretKeySpec = {
    var keyBytes: Array[Byte] = (SALT + key).getBytes("UTF-8")
    val sha: MessageDigest = MessageDigest.getInstance("SHA-1")
    keyBytes = sha.digest(keyBytes)
    keyBytes = util.Arrays.copyOf(keyBytes, 16)
    new SecretKeySpec(keyBytes, "AES")
  }

}

@main def hello(): Unit =
  import PII._
  val john     = Name.fromString("John Doe")
  val johnMail = Email.fromString("john@doe.com")
  println(s"Name: ${john} Mail: ${johnMail}")

  val johnCleatText     = Name.fromName(john.get)
  val johnMailCleatText = Email.fromEmail(johnMail.get)
  println(s"Name: ${johnCleatText} Mail: ${johnMailCleatText}")