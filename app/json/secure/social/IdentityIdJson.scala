package json.secure.social

import securesocial.core.IdentityId
import play.api.libs.json.Json

object IdentityIdJson {

  val keyUserId = "userId"
  val keyProviderId = "providerId"
  implicit val identityIdFormat = Json.format[IdentityId]
}