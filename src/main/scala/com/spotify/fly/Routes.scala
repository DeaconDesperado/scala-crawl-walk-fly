package com.spotify.fly

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.spotify.crawl.User

trait Routes extends SprayJsonSupport {
  this: Service =>

  val dummyUser = User("davidfoobar", "foobar", 29)

  sealed trait Login
  case class LoginSucceeded(user:User) extends Login
  case object LoginFailed extends Login

  def login(request:User) = userRepo.get(request.username).map {
    case Some(user) if user.password == request.password =>
      LoginSucceeded(user)
    case _ => LoginFailed
  }

  val loginRoute = {
    path("login") {
      post {
        entity(as[User]){ loginRequest =>
          onSuccess(login(loginRequest)){
            case LoginSucceeded(user) =>
              complete(user)
            case LoginFailed =>
              complete(StatusCodes.Forbidden -> "Login failed")
          }
        }
      }
    } ~
    path("users"){
      post {
        entity(as[User]){ user =>
          onSuccess(userRepo.put(user)){
            complete(StatusCodes.OK -> "User created")
          }
        }
      }
    }
  }
}
