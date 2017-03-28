package com.spotify.fly

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.spotify.fly.User
import spray.json.DefaultJsonProtocol._
import spray.json.{JsNull, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

import scala.collection.immutable.Map


trait Serialization extends SprayJsonSupport {
  implicit object UserTypesFormat extends RootJsonFormat[UserTypes]{
    def write(ut:UserTypes) = ut match {
      case PremiumUser(Paypal(paypalEmail)) =>
        JsObject("payment" -> JsString("paypal"), "paypal_email" -> JsString(paypalEmail))
      case PremiumUser(AuthorizeNet) =>
        JsObject("payment" -> JsString("authorize.net"))
      case FreeUser =>
        JsString("free")
    }

    override def read(json: JsValue) =  {
      json match {
        case JsString("free") =>
          FreeUser
        case JsObject(fields) =>
          fields.toList.sortBy(_._1) match {
            case ("payment", JsString("authorize.net")) :: Nil =>
              PremiumUser(AuthorizeNet)
            case ("payment", JsString("paypal")) :: ("paypal_email", JsString(email)) ::Nil =>
              PremiumUser(Paypal(email))
          }

      }
    }
  }

  implicit val userFormat = jsonFormat4(User)
  implicit val loginFormat = jsonFormat2(LoginRequest)

}
