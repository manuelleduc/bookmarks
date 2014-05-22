package service

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import json.secure.social.IdentityIdJson
import json.secure.social.SocialUserJson
import json.secure.social.SocialUserJson.socialUserFormat
import json.secure.social.TokenJson
import json.secure.social.TokenJson.tokenFormat
import play.Logger
import play.api.Application
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json.collection.JSONCollection
import securesocial.core.Identity
import securesocial.core.IdentityId
import securesocial.core.SocialUser
import securesocial.core.UserServicePlugin
import securesocial.core.providers.Token

// TODO : Factoriser les opérations de récupération de données.
class UserService(application: Application) extends UserServicePlugin(application) {

  lazy val db = ReactiveMongoPlugin.db
  lazy val collectionIdentity = db.collection[JSONCollection]("identity")
  lazy val collectionToken = db.collection[JSONCollection]("token")
  implicit val identityIdJson = Json.format[IdentityId]
  import json.secure.social.TokenJson.tokenFormat
  import json.secure.social.SocialUserJson.socialUserFormat

  override def find(id: IdentityId): Option[Identity] = {

    Logger.debug(s"find(id=$id)")
    val keyIdentityId = SocialUserJson.keyIdentityId
    val keyProviderId = IdentityIdJson.keyProviderId
    val keyUserId = IdentityIdJson.keyUserId
    val query = Json.obj("$and" -> Json.arr(Json.obj(s"$keyIdentityId.$keyProviderId" -> id.providerId),
      Json.obj(s"$keyIdentityId.$keyUserId" -> id.userId)))
    val find = collectionIdentity.
      find(query)
    val cursor = find.one[SocialUser]
    val res = cursor map { x =>
      x
    } recover {
      case _ => None
    }

    Await.result(cursor, 5000 millis)
  }

  override def deleteExpiredTokens() = {
    collectionToken.find(Json.obj()).cursor[Token].collect[List]() map { l =>
      l.map { t =>
        if (t.isExpired) {
          deleteToken(t.uuid)
        }
      }
    }
  }

  override def deleteToken(uuid: String) = {
    Logger.debug(s"deleteToken(uuid=$uuid)")
    collectionToken.remove(Json.obj(TokenJson.keyUuid -> uuid))
  }

  override def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = {
    Logger.debug(s"findByEmailAndProvider(email=$email; providerId=$providerId)")
    val keyIdentityId = SocialUserJson.keyIdentityId
    val keyProviderId = IdentityIdJson.keyProviderId
    val query = Json.obj("$and" ->
      Json.arr(Json.obj(SocialUserJson.keyEmail -> email),
        Json.obj(s"$keyIdentityId.$keyProviderId" -> providerId)))
    Logger.debug(s"query=$query")
    val find = collectionIdentity.find(query)
    val cursor = find.one[SocialUser]
    val res = cursor map { x =>
      x
    } recover {
      case _ => None
    }

    Await.result(cursor, 5000 millis)
  }

  override def findToken(token: String): Option[Token] = {
    Logger.debug(s"findToken(token=$token")
    val find = collectionToken.find(Json.obj(TokenJson.keyUuid -> token))
    val cursor = find.one[Token]

    val res = cursor map { x =>
      x
    } recover {
      case _ => None
    }

    Await.result(cursor, 5000 millis)
  }

  override def save(user: Identity): Identity = {
    Logger.debug(s"save(user=$user)")
    val u = SocialUser(user)
    val result = collectionIdentity.insert(u).map { _ =>
      u
    }
    Await.result(result, 5000 millis)
  }

  override def save(token: Token) = {
    Logger.debug(s"save(token=$token)")
    val result = collectionToken.insert(token).map { _ =>
      token
    }
    Await.result(result, 5000 millis)
  }
}