package service

import scala.concurrent.Await
import play.api.Application
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.json.Reads
import play.modules.reactivemongo.ReactiveMongoPlugin
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson._
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentReader
import securesocial.core._
import securesocial.core.IdentityId
import securesocial.core.providers.Token
import json.secure.social.SocialUserJson
import json.secure.social.IdentityIdJson
import json.secure.social.TokenJson
import scala.concurrent.duration._
import play.Logger

// TODO : Factoriser les opérations de récupération de données.
class UserService(application: Application) extends UserServicePlugin(application) {

  lazy val db = ReactiveMongoPlugin.db
  lazy val collectionIdentity = db.collection[BSONCollection]("identity")
  lazy val collectionToken = db.collection[BSONCollection]("token")
  implicit val identityIdJson = Json.format[IdentityId]
  implicit val socialUserReader = json.secure.social.SocialUserJson.Reader
  implicit val tokenReader = json.secure.social.TokenJson.Reader
  implicit val socialUserWriter = json.secure.social.SocialUserJson.Writer
  implicit val tokenWriter = json.secure.social.TokenJson.Writer

  override def find(id: IdentityId): Option[Identity] = {

    Logger.debug(s"find(id=$id)")
    val keyIdentityId = SocialUserJson.keyIdentityId
    val keyProviderId = IdentityIdJson.keyProviderId
    val keyUserId = IdentityIdJson.keyUserId
    val query = BSONDocument("$and" -> BSONArray(BSONDocument(s"$keyIdentityId.$keyProviderId" -> id.providerId),
      BSONDocument(s"$keyIdentityId.$keyUserId" -> id.userId)))
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
    collectionToken.find(BSONDocument()).cursor[Token].collect[List]() map { l =>
      l.map { t =>
        if (t.isExpired) {
          deleteToken(t.uuid)
        }
      }
    }
  }

  override def deleteToken(uuid: String) = {
    Logger.debug(s"deleteToken(uuid=$uuid)")
    collectionToken.remove(BSONDocument(TokenJson.keyUuid -> uuid))
  }

  override def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = {
    Logger.debug(s"findByEmailAndProvider(email=$email; providerId=$providerId)")
    val keyIdentityId = SocialUserJson.keyIdentityId
    val keyProviderId = IdentityIdJson.keyProviderId
    val query = BSONDocument("$and" ->
      BSONArray(BSONDocument(SocialUserJson.keyEmail -> email),
        BSONDocument(s"$keyIdentityId.$keyProviderId" -> providerId)))
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
    val find = collectionToken.find(BSONDocument(TokenJson.keyUuid -> token))
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