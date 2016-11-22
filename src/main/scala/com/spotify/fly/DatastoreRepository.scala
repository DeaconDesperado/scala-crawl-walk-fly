package com.spotify.fly

import com.google.cloud.datastore._

import scala.concurrent.{ExecutionContext, Future}
import collection.JavaConverters._

class DatastoreRepository[T : DatastoreMapper](implicit val ec:ExecutionContext) extends Repository[T]{

  //Obtain the mapper the context bound guarantees
  val mapper = implicitly[DatastoreMapper[T]]

  //Connect to datastore
  val datastore:Datastore = DatastoreOptions.newBuilder()
    .setNamespace("scala-workshop")
    .setProjectId("datatime-sandbox")
    .build().getService

  /**
    * Get an object by ID
    * @param id
    * @return
    */
  override def get(id: String): Future[Option[T]] = Future {
    val key = datastore.newKeyFactory().setKind(mapper.kind).newKey(id)
    Option(datastore.get(key, ReadOption.eventualConsistency()))
        .map(mapper.fromDatastoreEntity)
  }

  /**
    * Put an object into datastore
    * @param model
    * @return
    */
  override def put(model: T): Future[Unit] = Future {
    val key = datastore.newKeyFactory()
      .setProjectId("datatime-sandbox")
      .setNamespace("scala-workshop")
      .setKind(mapper.kind)
      .newKey(mapper.keyProperty(model))

    val entity = Entity.newBuilder(mapper.toDatastoreEntity(model, key))
        .setKey(key)
        .build()
    datastore.put(entity)
  }

  /**
    * Get all entities of a kind
    * @return
    */
  def all():Future[Seq[T]] = Future {
    val query = Query.newEntityQueryBuilder()
        .setKind(mapper.kind)
        .build()

    datastore.run(query, ReadOption.eventualConsistency())
      .asScala
      .toSeq
      .map(mapper.fromDatastoreEntity)
  }
}

trait DatastoreMapper[T] {
  def kind:String
  def keyProperty(model:T):String
  def toDatastoreEntity(model:T, key:Key):Entity
  def fromDatastoreEntity(entity:Entity):T
}
