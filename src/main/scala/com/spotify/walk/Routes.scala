package com.spotify.walk

import akka.http.scaladsl.server.Directives._
import com.spotify.crawl.User

case class LoginRequest(userName:String)

trait Routes {
  this: Serialization =>

  val dummyUser = User("davidfoobar", 20, true)

  val loginRoute = {
    path("login") {
      entity(as[LoginRequest]){ login =>
        complete(dummyUser)
      }
    }
  }
}
