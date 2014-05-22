package json.secure.social

import play.api.libs.json.Json
import securesocial.core.OAuth1Info

object OAuth1InfoJson {

  val keyToken = "token"
  val keySecret = "secret"
  implicit val oAuth1InfoFormat = Json.format[OAuth1Info]
}