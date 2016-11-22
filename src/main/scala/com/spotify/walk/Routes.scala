package com.spotify.walk

import akka.http.scaladsl.server.Directives._
import com.spotify.crawl.User

trait Routes {
  this: Serialization =>

  val dummyUser = User("davidfoobar", "foobar", 20)

  val loginRoute = {
    path("login") {
      complete(dummyUser)
    }
  }
}
