package com.spotify.crawl

object Extractors extends App {

  case class Scoreboard(gameId:String, finalScore:Int)

  val games = List(
    Scoreboard("abc", 200),
    Scoreboard("123", 204),
    Scoreboard("xyz", 679)
  )

  //Lists have a sequence unapply method that can use
  //the cons (::) operator to extract the head and tail
  def bestScore(l:List[Scoreboard], curMax:Int = 0):Int = l match {
    case Nil =>
      curMax
    case Scoreboard(_, score) :: tail if score > curMax =>
      bestScore(tail, score)
    case Scoreboard(_, score) :: tail =>
      bestScore(tail, curMax)
  }

  println(bestScore(games))
}
