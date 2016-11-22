package com.spotify.walk

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Main extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  new Service().run()
}

class Service ()(
  implicit val system: ActorSystem,
  implicit val materializer: ActorMaterializer,
  implicit val ec:ExecutionContext
){

  val route =
    path("hello") {
      get {
        complete("<h1>Say hello to akka-http</h1>")
      }
    }

  def run() {
    val bindingFuture = Http().bindAndHandle(route, "localhost", 8081)

    println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
