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

  implicit object Reader extends BSONDocumentReader[SocialUser] {

    def read(doc: BSONDocument): SocialUser = {

      implicit val identityIdReader = json.secure.social.IdentityIdJson.Reader
      implicit val authentificationMethodReader = json.secure.social.AuthenticationMethodJson.Reader
      implicit val oAuth1InfoReader = json.secure.social.OAuth1InfoJson.Reader
      implicit val oAuth2InfoReader = json.secure.social.OAuth2InfoJson.Reader
      implicit val passwordInfoReader = json.secure.social.PasswordInfoJson.Reader

      val identityId = doc.getAs[IdentityId](keyIdentityId).get
      val firstName = doc.getAs[String](keyFirstName).get
      val lastName = doc.getAs[String](keyLastName).get
      val fullName = doc.getAs[String](keyFullName).get
      val email = doc.getAs[String](keyEmail)
      val avatarUrl = doc.getAs[String](keyAvatarUrl)
      val authMethod = doc.getAs[AuthenticationMethod](keyAuthMethod).get
      val oAuth1Info = doc.getAs[OAuth1Info](keyOAuth1Info)
      val oAuth2Info = doc.getAs[OAuth2Info](keyOAuth2Info)
      val passwordInfo = doc.getAs[PasswordInfo](keyPasswordInfo)

      SocialUser(identityId, firstName, lastName, fullName, email,
        avatarUrl, authMethod,
        oAuth1Info,
        oAuth2Info,
        passwordInfo)
    }
  }
  
  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[SocialUser] {
    override def write(socialUser: SocialUser): BSONDocument = {

      implicit val identityIdWriter = json.secure.social.IdentityIdJson.Writer
      implicit val authMethodWriter = json.secure.social.AuthenticationMethodJson.Writer
      implicit val oAuth1InfoWriter = json.secure.social.OAuth1InfoJson.Writer
      implicit val oAuth2InfoWriter = json.secure.social.OAuth2InfoJson.Writer
      implicit val passwordInfoWriter = json.secure.social.PasswordInfoJson.Writer
      
      BSONDocument(keyIdentityId -> socialUser.identityId,
        keyFirstName -> socialUser.firstName,
        keyLastName -> socialUser.lastName,
        keyFullName -> socialUser.fullName,
        keyEmail -> socialUser.email,
        keyAvatarUrl -> socialUser.avatarUrl,
        keyAuthMethod -> socialUser.authMethod,
        keyOAuth1Info -> socialUser.oAuth1Info,
        keyOAuth2Info -> socialUser.oAuth2Info,
        keyPasswordInfo -> socialUser.passwordInfo)
    }
  }
  
  implicit val writer = Writer
}