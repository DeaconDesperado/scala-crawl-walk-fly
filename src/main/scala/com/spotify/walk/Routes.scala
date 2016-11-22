package com.spotify.walk

import akka.http.scaladsl.server.Directives._
import com.spotify.walk.models.User

trait Routes {
  this: Serialization =>

  val dummyUser = User("davidfoobar", "foobar")

  val loginRoute = {
    path("login") {
      complete(dummyUser)
    }
  }
}
