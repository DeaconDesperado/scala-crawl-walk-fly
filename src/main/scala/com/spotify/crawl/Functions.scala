package com.spotify.crawl

import scala.annotation.tailrec

object Functions extends App {

  //A function definition
  def incr(x: Int):Int = x + 1

  //Compiler can infer return type
  def decr(x: Int) = x - 1

  //A tail-recursive summation :)
  @tailrec
  def sum(x:Int, y:Int):Int = y match {
    case 0 => x
    case _ => sum(incr(x), decr(y))
  }

  val words = "Hello" :: "friend" :: "how" :: "are" :: "you?" :: Nil

  val out = words.foldRight("")((x, y) => x + "\n" + y)
  println(out)

  val sumWithFour = sum(4, _:Int)

  println(sumWithFour(20))

  def f(s: String) = "f(" + s + ")"

  def g(s: String) = "g(" + s + ")"

  val fComposeG = f _ compose g _

  val fAndThenG = f _ andThen g _

  println(fComposeG("hello"))
  println(fAndThenG("world"))

}
