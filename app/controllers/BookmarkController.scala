package controllers

import play.api._
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import reactivemongo.api.collections.default.BSONCollection
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

object BookmarkController extends Controller with MongoController with SecureSocial {
  implicit val collectionBookmark = db.collection[BSONCollection]("bookmark")
  def list = Action.async {
    val json = BookmarkService.list()(collectionBookmark)

    implicit val bookmarkWrites = new Writes[Bookmark] {
      def writes(bookmark: Bookmark) = Json.obj(
        "_id" -> bookmark._id,
        "title" -> bookmark.title,
        "link" -> bookmark.link,
        "comment" -> bookmark.comment)
    }

    json.map { j =>
      val a = j.foldRight(Json.arr())((x, y) => {
        y.append(Json.toJson(x))
      })
      Ok(a)
    }
  }
}