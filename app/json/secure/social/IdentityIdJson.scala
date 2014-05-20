package json.secure.social

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import securesocial.core.IdentityId

object IdentityIdJson {

  val keyUserId = "userId"
  val keyProviderId = "providerId"
    
  implicit object Reader extends BSONDocumentReader[IdentityId] {
    def read(doc: BSONDocument): IdentityId = {
      val userId = doc.getAs[String](keyUserId).get
      val providerId = doc.getAs[String](keyProviderId).get
      IdentityId(userId, providerId)
    }
  }
  
  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[IdentityId] {
    override def write(identityId: IdentityId): BSONDocument = {
      BSONDocument(keyUserId -> identityId.userId,
        keyProviderId -> identityId.providerId)
    }
  }
  
  implicit val writer = Writer
}