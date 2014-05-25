package json.playframework

import play.api.libs.json._
import securesocial.core._
import play.api.libs.functional.syntax._
import play.api.data.FormError

object FormErrorJson {

  val keyKey = "key"
  val keyMessage = "message"
  implicit val formErrorReads: Reads[FormError] = ((__ \ keyKey).read[String] and
    (__ \ keyMessage).read[String])((key, message) => FormError(key, message))

  val formErrorWrites: Writes[FormError] = ((__ \ keyKey).write[String] and
    (__ \ keyMessage).write[String])((e: FormError) => (e.key, e.message))

  implicit val formErrorFormat = Format(formErrorReads, formErrorWrites)
}