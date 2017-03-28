package com.spotify.fly

import com.google.cloud.datastore.{IncompleteKey, EntityValue, Key, Entity}
import com.google.common.collect.ImmutableList
import com.spotify.fly.User

object UserMapper extends DatastoreMapper[User]{

  override def kind: String = "user"

  override def keyProperty(model: User): String = model.name

  override def toDatastoreEntity(model: User, key: Key): Entity = {

    val userTypeRow = model.userType match {
      case PremiumUser(AuthorizeNet) =>
        "premium|authorize"
      case PremiumUser(Paypal(email)) =>
        s"premium|paypal|$email"
      case FreeUser =>
        "free"
    }

    Entity.newBuilder(key)
      .set("username", model.name)
      .set("password", model.password)
      .set("age", model.age.toLong)
      .set("userType", userTypeRow)
      .build()
  }

  override def fromDatastoreEntity(entity: Entity): User = {

    val userType:UserTypes = entity.getString("userType").split('|').toList match {
      case "free" :: Nil =>
        FreeUser
      case "premium" :: "paypal" :: email :: Nil =>
        PremiumUser(Paypal(email))
      case "premium" :: "authorize" :: Nil =>
        PremiumUser(AuthorizeNet)
      case _ =>
        FreeUser
    }

    User(
      entity.getString("username"),
      entity.getLong("age").toInt,
      entity.getString("password"),
      userType
    )
  }
}
