package com.spotify.crawl

final case class User(username:String, password:String, age:Int)

object Main extends App {

  val people = List(
    User("Bob", "test",41),
    User("Catherine", "test",23),
    User("Cindy", "test",52)
  )

  val maybeCindy = people.find(_.username == "Cindy")

  //maybeCindy is an option
  maybeCindy match {
    case Some(p) ⇒
      println(s"${p.username} was found!\n")
    case None ⇒
      println("nobody's home!\n")
  }

  people.filter(_.age < 50).map(_.username)

  //I want a list of usernames for people younger than 50 whose username starts with the letter C
  val cYoungPeople = people.collect {
    case User(username, pass, age) if username.startsWith("C") && age < 50 => username
  }

  cYoungPeople.foreach(println)
}
