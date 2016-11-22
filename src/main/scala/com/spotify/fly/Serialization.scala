package com.spotify.fly

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.crawl.User
import spray.json.DefaultJsonProtocol._

trait Serialization extends SprayJsonSupport {

  implicit val userFormat = jsonFormat3(User)

}
