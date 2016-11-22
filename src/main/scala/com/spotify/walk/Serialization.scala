package com.spotify.walk

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.crawl.User
import spray.json.DefaultJsonProtocol._

trait Serialization extends SprayJsonSupport {

  implicit val itemFormat = jsonFormat3(User)

}
