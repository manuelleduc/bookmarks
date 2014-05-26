package controllers

import play.api._
import play.api.mvc._
import securesocial.core.SecureSocial

object Application extends Controller with SecureSocial {

  def index = UserAwareAction {
    Ok(views.html.index())
  }

  def jsRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        BookmarkApiController.list,
        BookmarkPartialsController.index,
        BookmarkPartialsController.create)).as("text/javascript")
  }
}