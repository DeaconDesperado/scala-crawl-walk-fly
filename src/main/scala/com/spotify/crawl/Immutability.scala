package com.spotify.crawl

object Immutability extends App {

  //Immutable value
  val name = "Bob blocker"

  var foo = "bar"

  //Reassignment to val, will not compile
  //name = "Bill Foobar"

  foo = "foobar"

  println(foo)

}
