package models

import reactivemongo.bson.BSONDocument
import play.api.libs.json.Json

case class Bookmark(_id: Option[String], title: String, link: String, comment: String, tags: List[Tag] = List())

object Bookmark {
//  implicit val formater = Json.format[Bookmark]
}