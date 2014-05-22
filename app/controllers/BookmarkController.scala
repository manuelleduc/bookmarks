package controllers

import play.api._
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import securesocial.core.SecureSocial
import service.BookmarkService
import json.BookmarkJson
import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Writes
import models.Bookmark
import play.api.libs.json.JsPath
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.modules.reactivemongo.json.collection.JSONCollection

object BookmarkController extends Controller with MongoController with SecureSocial {
  implicit val collectionBookmark = db.collection[JSONCollection]("bookmark")
  import json.BookmarkJson.bookmarkFormat
  def list = Action.async {
    val json = BookmarkService.list()(collectionBookmark)

    json.map { j =>
      Ok(Json.toJson(j))
    }
  }
}