package json.secure.social

import securesocial.core.AuthenticationMethod
import play.api.libs.json.Json

object AuthenticationMethodJson {
  val keyMethod = "method"
  implicit val authenticationMethodFormat = Json.format[AuthenticationMethod]
}