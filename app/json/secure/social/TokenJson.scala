package json.secure.social

import play.api.libs.json._
import play.api.libs.functional.syntax._
import securesocial.core.providers.Token
import org.joda.time.DateTime

object TokenJson {

  val keyUuid = "uuid"
  val keyEmail = "email"
  val keyCreationTime = "creationTime"
  val keyExpirationTime = "expirationTime"
  val keyIsSignUp = "isSignUp"

  implicit val tokenFormat = Json.format[Token]
}