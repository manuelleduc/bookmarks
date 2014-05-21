package json

import models.Bookmark
import reactivemongo.bson.BSONDocumentReader
import securesocial.core.IdentityId
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONObjectID

object BookmarkJson {

  val keyId = "_id"
  val keyTitle = "title"
  val keyLink = "link"
  val keyComment = "comment"

  implicit object Reader extends BSONDocumentReader[Bookmark] {
    def read(doc: BSONDocument): Bookmark = {
      val id = doc.getAs[BSONObjectID](keyId).map(_.toString)
      val title = doc.getAs[String](keyTitle).get
      val link = doc.getAs[String](keyLink).get
      val comment = doc.getAs[String](keyComment).get
      Bookmark(id, title, link, comment)
    }
  }

  implicit val reader = Reader

  implicit object Writer extends BSONDocumentWriter[Bookmark] {
    override def write(bookmark: Bookmark): BSONDocument = {
      BSONDocument(keyId -> bookmark._id,
        keyTitle -> bookmark.title,
        keyLink -> bookmark.link,
        keyComment -> bookmark.comment)
    }
  }

  implicit val writer = Writer
}