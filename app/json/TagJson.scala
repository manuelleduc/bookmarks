package json

import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.Tag

object TagJson {
  implicit val tagFormat = Json.format[Tag]
}