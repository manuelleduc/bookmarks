package controllers

import play.api._
import play.api.mvc._
import securesocial.core.SecureSocial
import forms.CreateBookmarkForm

object Application extends Controller with SecureSocial {

  def index = UserAwareAction {
    Ok(views.html.index())
  }

}