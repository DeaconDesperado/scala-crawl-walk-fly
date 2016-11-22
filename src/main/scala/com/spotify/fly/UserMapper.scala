package com.spotify.fly

import com.google.cloud.datastore.{IncompleteKey, EntityValue, Key, Entity}
import com.google.common.collect.ImmutableList
import com.spotify.walk.models.User

object UserMapper extends DatastoreMapper[User]{

  override def kind: String = "user"

  override def keyProperty(model: User): String = model.username

  override def toDatastoreEntity(model: User, key: Key): Entity = {
    Entity.newBuilder(key)
      .set("username", model.username)
      .set("password", model.password)
      .build()
  }

  override def fromDatastoreEntity(entity: Entity): User = User(
    entity.getString("username"),
    entity.getString("password")
  )
}
