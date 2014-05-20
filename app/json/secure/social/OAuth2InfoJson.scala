package json.secure.social

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.OAuth2Info

object OAuth2InfoJson {
  private val keyAccessToken = "accessToken"
  private val keyTokenType = "tokenType"
  private val keyExpiresIn = "expiresIn"
  private val keyRefreshToken = "refreshToken"

  implicit object Reader extends BSONDocumentReader[OAuth2Info] {
    def read(doc: BSONDocument): OAuth2Info = {
      val accessToken = doc.getAs[String](keyAccessToken).get
      val tokenType = doc.getAs[String](keyTokenType)
      val expiresIn = doc.getAs[Int](keyExpiresIn)
      val refreshToken = doc.getAs[String](keyRefreshToken)
      OAuth2Info(accessToken, tokenType,
        expiresIn, refreshToken)
    }
  }
  
  implicit val reader  =Reader

  implicit object Writer extends BSONDocumentWriter[OAuth2Info] {
    override def write(oAuth2Info: OAuth2Info): BSONDocument = {
      BSONDocument(keyAccessToken -> oAuth2Info.accessToken,
        keyTokenType -> oAuth2Info.tokenType,
        keyExpiresIn -> oAuth2Info.expiresIn,
        keyRefreshToken -> oAuth2Info.refreshToken)
    }
  }
  
  implicit val writer = Writer
}