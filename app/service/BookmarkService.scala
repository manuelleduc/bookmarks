package service

import scala.concurrent.Future
import scala.concurrent.impl.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import models.Bookmark
import play.modules.reactivemongo.json.collection.JSONCollection

object BookmarkService {
  def list()(implicit collection: JSONCollection): Future[List[Bookmark]] = {
    import json.BookmarkJson.bookmarkFormat
    collection.find(Json.obj()).cursor[Bookmark].collect[List]()
  }
}