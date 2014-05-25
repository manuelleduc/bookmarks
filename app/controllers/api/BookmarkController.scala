package controllers.api
import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import securesocial.core.SecureSocial
import service.BookmarkService
import json.BookmarkJson.bookmarkFormat
import forms.CreateBookmarkForm
import play.api.data.FormError
import play.api.i18n.Messages
import scala.concurrent.Future

object BookmarkController extends Controller with MongoController with SecureSocial {
  implicit val collectionBookmark = db.collection[JSONCollection]("bookmark")
  import json.BookmarkJson.bookmarkFormat
  def list = UserAwareAction { implicit request =>
    Async {
      val user = request.user
      val json = BookmarkService.list(user)
      json.map { j =>
        Ok(Json.toJson(j))
      }
    }
  }

  def create = UserAwareAction { implicit request =>
    Async {
      val form = CreateBookmarkForm.form
      form.bindFromRequest.fold(formWithErrors => {
        import scala.concurrent.Future
        val errors = formWithErrors.errors
        import json.playframework.FormErrorJson.formErrorFormat
        Future {
          BadRequest(Json.toJson(errors.map(f => {
            val m = Messages(f.message)
            FormError(f.key, m)
          })))
        }
      }, bookmark => {
        request.user match {
          case Some(user) => {
            BookmarkService.create(bookmark, user) map { lastError =>
              Created
            }
          }
          case None => Future { BadRequest("User not allowed") }
        }
      })
    }
  }
}
	