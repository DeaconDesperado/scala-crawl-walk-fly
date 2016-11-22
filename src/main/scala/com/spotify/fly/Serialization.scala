package com.spotify.fly

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.walk.models.User
import spray.json.DefaultJsonProtocol._

trait Serialization extends SprayJsonSupport {

  implicit val userFormat = jsonFormat2(User)

}
