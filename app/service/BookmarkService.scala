package service

import scala.concurrent.Future
import scala.concurrent.impl.Future
import models.Bookmark
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.core.commands.LastError
import org.joda.time.DateTime
import securesocial.core.Identity

object BookmarkService {
  import json.BookmarkJson.bookmarkFormat

  def list(user: Option[Identity])(implicit collection: JSONCollection): Future[List[Bookmark]] = {
    val conditions = user match {
      case None => Json.obj("public" -> true)
      case Some(user) => {
        Json.obj("identityId" -> user.identityId.userId)
      }
    }

    collection.find(conditions).cursor[Bookmark].collect[List]()
  }

  def create(bookmark: Bookmark, user: Identity)(implicit collection: JSONCollection): Future[LastError] = {
    val t = Some(new DateTime())
    val b = bookmark.copy(identityId = Some(user.identityId.userId),
      creationDate = t, modificationDate = t)
    collection.insert(b)
  }
}