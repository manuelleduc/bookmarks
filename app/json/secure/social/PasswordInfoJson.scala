package json.secure.social

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.PasswordInfo

object PasswordInfoJson {
  private val keyHasher = "hasher"
  private val keyPassword = "password"
  private val keySalt = "salt"

  implicit object Reader extends BSONDocumentReader[PasswordInfo] {
    def read(doc: BSONDocument): PasswordInfo = {
      val hasher = doc.getAs[String](keyHasher).get
      val password = doc.getAs[String](keyPassword).get
      val salt = doc.getAs[String](keySalt)
      PasswordInfo(hasher, password, salt)
    }
  }
  
  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[PasswordInfo] {
    override def write(passwordInfo: PasswordInfo): BSONDocument = {
      BSONDocument(keyHasher -> passwordInfo.hasher,
        keyPassword -> passwordInfo.password,
        keySalt -> passwordInfo.salt)
    }
  }
  
  implicit val writer = Writer
}