package controllers.partials

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

object BookmarkController extends Controller with MongoController with SecureSocial {
  def index = Action {
    Ok(views.html.partials.index())
  }

  def create = Action {
    val form = forms.CreateBookmarkForm.form
    Ok(views.html.partials.create(form))
  }
}