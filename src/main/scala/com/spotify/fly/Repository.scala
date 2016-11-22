package com.spotify.fly

import scala.concurrent.Future

trait Repository[A] {
  def get(id:String):Future[Option[A]]
  def put(entity:A):Future[Unit]
  def all():Future[Seq[A]]
}
