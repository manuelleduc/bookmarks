package json.secure.social

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.AuthenticationMethod

object AuthenticationMethodJson {

  private val keyMethod = "method"
  
  implicit object Reader extends BSONDocumentReader[AuthenticationMethod] {
    override def read(doc: BSONDocument): AuthenticationMethod = {
      val method = doc.getAs[String](keyMethod).get
      AuthenticationMethod(method)
    }
  }
  
  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[AuthenticationMethod] {
    override def write(authenticationMethod: AuthenticationMethod): BSONDocument = {
      BSONDocument(keyMethod -> authenticationMethod.method)
    }
  }
  
  implicit val writer = Writer
}