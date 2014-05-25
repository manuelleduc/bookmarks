package forms

import play.api.data._
import play.api.data.Forms._
import models.Bookmark
import play.api.data.validation.Constraints._
import reactivemongo.bson.BSONObjectID
import models.Tag
import org.joda.time.DateTime

object CreateBookmarkForm {
  val form = Form(mapping(
    "id" -> ignored(Option(null.asInstanceOf[BSONObjectID])),
    "title" -> text.verifying(nonEmpty),
    "link" -> text.verifying(nonEmpty),
    "comment" -> optional(text),
    "identityId" -> ignored(Option(null.asInstanceOf[String])),
    "public" -> boolean,
    "creationDate" -> ignored(Option(null.asInstanceOf[DateTime])),
    "modificationDate" -> ignored(Option(null.asInstanceOf[DateTime])),
    "tags" -> ignored(Option(null.asInstanceOf[Seq[Tag]])))(Bookmark.apply)(Bookmark.unapply))
}