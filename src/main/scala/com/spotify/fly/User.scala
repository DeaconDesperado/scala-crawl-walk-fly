package com.spotify.fly

sealed trait PaymentMethod
case object AuthorizeNet extends PaymentMethod
case class Paypal(paypalEmail:String) extends PaymentMethod

sealed trait UserTypes
case object FreeUser extends UserTypes
case class PremiumUser(paymentMethod:PaymentMethod) extends UserTypes

case class User(
  name:String,
  age:Int,
  password:String,
  userType: UserTypes
)
