package com.spotify.fly

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

case class LoginRequest(username:String, password:String)

trait Routes extends SprayJsonSupport {
  this: Service =>

  val loginRoute = {
    path("login") {
      post {
        entity(as[LoginRequest]){ loginRequest =>
          onSuccess(userRepo.get(loginRequest.username)){
            case Some(User(name, _, pass, PremiumUser(_))) if pass == loginRequest.password =>
              complete(StatusCodes.OK, s"Welcome to premium, $name")
            case Some(User(name, _, pass, FreeUser)) if pass == loginRequest.password =>
              complete(StatusCodes.OK, s"Welcome $name, we suggest you upgrade!")
            case _ =>
              complete(StatusCodes.Forbidden, "The login credentials were not valid")
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
