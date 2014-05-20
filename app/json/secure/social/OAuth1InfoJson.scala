package json.secure.social

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.OAuth1Info

object OAuth1InfoJson {

  private val keyToken = "token"
  private val keySecret = "secret"

  implicit object Reader extends BSONDocumentReader[OAuth1Info] {
    def read(doc: BSONDocument): OAuth1Info = {
      val token = doc.getAs[String](keyToken).get
      val secret = doc.getAs[String](keySecret).get
      OAuth1Info(token, secret)
    }
  }
  
  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[OAuth1Info] {
    override def write(oAuth1Info: OAuth1Info): BSONDocument = {
      BSONDocument(keyToken -> oAuth1Info.token,
        keySecret -> oAuth1Info.secret)
    }
  }
  
  implicit val writer = Writer
}