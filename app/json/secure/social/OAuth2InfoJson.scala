package json.secure.social

import play.api.libs.json.Json
import securesocial.core.OAuth2Info

object OAuth2InfoJson {
  val keyAccessToken = "accessToken"
  val keyTokenType = "tokenType"
  val keyExpiresIn = "expiresIn"
  val keyRefreshToken = "refreshToken"
  implicit val oAuth2InfoFormat = Json.format[OAuth2Info]
}