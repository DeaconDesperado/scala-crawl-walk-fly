package com.spotify.crawl

object PartialFunctions extends App {


  val nameOfYoungerEmployee:PartialFunction[User, String] = {
    case User(name, age, true) if age < 35 => name
  }

  val nameOfOlderEmployee:PartialFunction[User, String] = {
    case User(name, age, true) if age > 45 => name
  }

  val people = List(
    User("bob", 55, true),
    User("cindy", 23, true),
    User("meredith", 29, true),
    User("chris", 29, false)
  )

  println("Younger employees: ")
  people.collect(nameOfYoungerEmployee).foreach(println)

  println("\nYounger and older employees: ")

  people.collect(nameOfYoungerEmployee.orElse(nameOfOlderEmployee)).foreach(println)

  //Will MatchError since no default _ case
  /*
  User("frank", 90, true) match {
    case User(name, age, false) => println("User found " + name)
  }
  */

  println("")

  //Match default, but use value
  User("frank", 90, true) match {
    case User(name, age, false) => println("User found " + name)
    case x => println(x)
  }

  //Match default, but discard value
  User("frank", 90, true) match {
    case User(name, age, false) => println("User found " + name)
    case _ => println("Nobody matched")
  }

  println("\n")

  val mapBadlyTypedMethod:PartialFunction[Any, String] = {
    case x:String => x
    case x:Int => "an int"
    case true => "a true bool"
    case false => "a false bool"
    case x:Double if x > 200.00 => "A big double"
    case x:Double => "a double less than 200"
    case _ => "Some other such thing"
  }

  List("heterogenous", 1, "list", 201.00, 45.00, true, User("bob", 17, true))
    .map(mapBadlyTypedMethod)
    .foreach(println)

}
