package com.spotify.crawl

object Main extends App {

  case class Person(name:String, age:Int)

  val people = List(
    Person("Bob", 41),
    Person("Catherine", 23),
    Person("Cindy", 52)
  )

  val maybeCindy = people.find(_.name == "Cindy")

  //maybeCindy is an option
  maybeCindy match {
    case Some(p) ⇒
      println(s"${p.name} was found!\n")
    case None ⇒
      println("nobody's home!\n")
  }

  people.filter(_.age < 50).map(_.name)

  //I want a list of names for people younger than 50 whose name starts with the letter C
  val cYoungPeople = people.collect {
    case Person(name, age) if name.startsWith("C") && age < 50 => name
  }

  cYoungPeople.foreach(println)
}
