package models

import play.api.libs.json.Json

case class Tag(_id: Option[String], name: String)

object Tag {
  implicit val convert = Json.format[Tag]
}