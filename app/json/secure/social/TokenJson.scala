package json.secure.social

import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.PasswordInfo
import securesocial.core.AuthenticationMethod
import securesocial.core.OAuth1Info
import securesocial.core.IdentityId
import securesocial.core.OAuth2Info
import securesocial.core.SocialUser
import securesocial.core.providers.Token
import org.joda.time.DateTime

object TokenJson {

  val keyUuid = "uuid"
  val keyEmail = "email"
  val keyCreationTime = "creationTime"
  val keyExpirationTime = "expirationTime"
  val keyIsSignUp = "isSignUp"

  implicit object Reader extends BSONDocumentReader[Token] {

    def read(doc: BSONDocument): Token = {
      val uuid = doc.getAs[String](keyUuid).get
      val email = doc.getAs[String](keyEmail).get
      val creationTime = doc.getAs[Long](keyCreationTime).map(new DateTime(_)).get
      val expirationTime = doc.getAs[Long](keyExpirationTime).map(new DateTime(_)).get
      val isSignUp = doc.getAs[Boolean](keyIsSignUp).get
      Token(uuid, email, creationTime, expirationTime, isSignUp)
    }
  }

  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[Token] {
    override def write(token: Token): BSONDocument =
      BSONDocument(keyUuid -> token.uuid,
        keyEmail -> token.email,
        keyCreationTime -> token.creationTime.getMillis,
        keyExpirationTime -> token.expirationTime.getMillis,
        keyIsSignUp -> token.isSignUp)
  }

  implicit val writer = Writer
}