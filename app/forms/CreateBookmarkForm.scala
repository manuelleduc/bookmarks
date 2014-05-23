package forms

import play.api.data._
import play.api.data.Forms._
import models.Bookmark
import play.api.data.validation.Constraints._
import reactivemongo.bson.BSONObjectID
import models.Tag

object CreateBookmarkForm {
  val form = Form(mapping(
    "id" -> ignored(Option(null.asInstanceOf[BSONObjectID])),
    "title" -> text.verifying(nonEmpty),
    "link" -> text.verifying(nonEmpty),
    "comment" -> text,
    "tags" -> ignored(Option(null.asInstanceOf[Seq[Tag]])))(Bookmark.apply)(Bookmark.unapply))
}