package json.secure.social

import securesocial.core.PasswordInfo
import play.api.libs.json.Json
import securesocial.core.PasswordInfo

object PasswordInfoJson {
  val keyHasher = "hasher"
  val keyPassword = "password"
  val keySalt = "salt"
  implicit val passwordInfoFormat = Json.format[PasswordInfo]
}