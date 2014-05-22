package json

import models.Bookmark
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo.json.BSONFormats._
import models.Tag

object BookmarkJson {

  val keyId = "_id"
  val keyTitle = "title"
  val keyLink = "link"
  val keyComment = "comment"
  import json.TagJson.tagFormat
  implicit val bookmarkFormat = Json.format[Bookmark]
}