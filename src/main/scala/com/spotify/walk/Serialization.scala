package com.spotify.walk

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.crawl.User
import spray.json.DefaultJsonProtocol._

trait Serialization extends SprayJsonSupport {

  implicit val userFormat = jsonFormat3[String, Int, Boolean, User](User)
  implicit val loginFormat = jsonFormat1[String, LoginRequest](LoginRequest)
}
