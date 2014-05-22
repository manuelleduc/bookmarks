package models

import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID

case class Bookmark(id: Option[BSONObjectID], title: String,
  link: String, comment: String, tags: Option[Seq[Tag]])
