package com.spotify.walk

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.walk.models.User
import spray.json.DefaultJsonProtocol._

trait Serialization extends SprayJsonSupport {

  implicit val itemFormat = jsonFormat2(User)

}
