package models

import play.api.libs.json.Json

case class Bookmark(_id: Option[String], title: String, link: String, comment: String, tags: List[Tag] = List())