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
    (JsPath \ keyIdentityId).read[IdentityId] and
    (JsPath \ keyFirstName).read[String] and
    (JsPath \ keyLastName).read[String] and
    (JsPath \ keyFullName).read[String] and
    (JsPath \ keyEmail).readNullable[String] and
    (JsPath \ keyAvatarUrl).readNullable[String] and
    (JsPath \ keyAuthMethod).read[AuthenticationMethod] and
    (JsPath \ keyOAuth1Info).readNullable[OAuth1Info] and
    (JsPath \ keyOAuth2Info).readNullable[OAuth2Info] and
    (JsPath \ keyPasswordInfo).readNullable[PasswordInfo])(
      (identityId, firstName, lastName, fullName, email, avatarUrl,
        authMethod, oAuth1Info, oAuth2Info, passwordInfo) =>
        SocialUser(identityId, firstName, lastName, fullName, email,
          avatarUrl, authMethod, oAuth1Info, oAuth2Info, passwordInfo))

  implicit val socialUserWrites: Writes[SocialUser] = (
    (JsPath \ keyIdentityId).write[IdentityId] and
    (JsPath \ keyFirstName).write[String] and
    (JsPath \ keyLastName).write[String] and
    (JsPath \ keyFullName).write[String] and
    (JsPath \ keyEmail).writeNullable[String] and
    (JsPath \ keyAvatarUrl).writeNullable[String] and
    (JsPath \ keyAuthMethod).write[AuthenticationMethod] and
    (JsPath \ keyOAuth1Info).writeNullable[OAuth1Info] and
    (JsPath \ keyOAuth2Info).writeNullable[OAuth2Info] and
    (JsPath \ keyPasswordInfo).writeNullable[PasswordInfo])(unlift(SocialUser.unapply))

  implicit val socialUserFormat = Format(socialUserReads, socialUserWrites)
}