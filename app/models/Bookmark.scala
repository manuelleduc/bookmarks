package models

import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID
import org.joda.time.DateTime

case class Bookmark(id: Option[BSONObjectID], title: String,
  link: String, comment: Option[String],
  identityId: Option[String], public: Boolean,
  creationDate: Option[DateTime],
  modificationDate: Option[DateTime], tags: Option[Seq[Tag]])
