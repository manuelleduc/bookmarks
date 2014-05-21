package service

import scala.concurrent.Future
import scala.concurrent.impl.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import reactivemongo.api.collections.default.BSONCollection
import play.api.libs.json.Json
import reactivemongo.bson.BSONDocument
import models.Bookmark

object BookmarkService {
  def list()(implicit collection: BSONCollection): Future[List[Bookmark]] = {
    import json.BookmarkJson.Reader
    collection.find(BSONDocument()).cursor[Bookmark].collect[List]()
  }
}