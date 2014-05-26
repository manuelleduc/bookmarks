package controllers

import play.api._
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import securesocial.core.SecureSocial

object BookmarkPartialsController extends Controller with MongoController with SecureSocial {
  def index = Action {
    Ok(views.html.partials.index())
  }

  def create = Action {
    val form = forms.CreateBookmarkForm.form
    Ok(views.html.partials.create(form))
  }
}