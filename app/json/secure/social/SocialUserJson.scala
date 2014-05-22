package json.secure.social

import play.api.libs.json._
import securesocial.core._
import play.api.libs.functional.syntax._

object SocialUserJson {

  val keyIdentityId = "identityId"
  val keyFirstName = "firstName"
  val keyLastName = "lastName"
  val keyFullName = "fullName"
  val keyEmail = "email"
  val keyAvatarUrl = "avatarUrl"
  val keyAuthMethod = "authMethod"
  val keyOAuth1Info = "oAuth1Info"
  val keyOAuth2Info = "oAuth2Info"
  val keyPasswordInfo = "passwordInfo"

  import json.secure.social.AuthenticationMethodJson.authenticationMethodFormat
  import json.secure.social.IdentityIdJson.identityIdFormat
  import json.secure.social.OAuth1InfoJson.oAuth1InfoFormat
  import json.secure.social.OAuth2InfoJson.oAuth2InfoFormat
  import json.secure.social.PasswordInfoJson.passwordInfoFormat

  implicit val socialUserReads: Reads[SocialUser] = (
    (__ \ keyIdentityId).read[IdentityId] and
    (__ \ keyFirstName).read[String] and
    (__ \ keyLastName).read[String] and
    (__ \ keyFullName).read[String] and
    (__ \ keyEmail).readNullable[String] and
    (__ \ keyAvatarUrl).readNullable[String] and
    (__ \ keyAuthMethod).read[AuthenticationMethod] and
    (__ \ keyOAuth1Info).readNullable[OAuth1Info] and
    (__ \ keyOAuth2Info).readNullable[OAuth2Info] and
    (__ \ keyPasswordInfo).readNullable[PasswordInfo])(
      (identityId, firstName, lastName, fullName, email, avatarUrl,
        authMethod, oAuth1Info, oAuth2Info, passwordInfo) =>
        SocialUser(identityId, firstName, lastName, fullName, email,
          avatarUrl, authMethod, oAuth1Info, oAuth2Info, passwordInfo))

  implicit val socialUserWrites: Writes[SocialUser] = (
    (__ \ keyIdentityId).write[IdentityId] and
    (__ \ keyFirstName).write[String] and
    (__ \ keyLastName).write[String] and
    (__ \ keyFullName).write[String] and
    (__ \ keyEmail).writeNullable[String] and
    (__ \ keyAvatarUrl).writeNullable[String] and
    (__ \ keyAuthMethod).write[AuthenticationMethod] and
    (__ \ keyOAuth1Info).writeNullable[OAuth1Info] and
    (__ \ keyOAuth2Info).writeNullable[OAuth2Info] and
    (__ \ keyPasswordInfo).writeNullable[PasswordInfo])(unlift(SocialUser.unapply))

  implicit val socialUserFormat = Format(socialUserReads, socialUserWrites)
}