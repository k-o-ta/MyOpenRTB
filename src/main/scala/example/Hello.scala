package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, post, path, concat}
import akka.stream.ActorMaterializer

import scala.io.StdIn

object App {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    val route =
      concat(
        path("hello") {
          get {
            complete(
              HttpEntity(
                ContentTypes.`text/html(UTF-8)`,
                "<h1>Say Hello to akka-http</h1>"
              )
            )
          }
        },
        path("bit") {
          post {
            complete(
              HttpEntity(
                ContentTypes.`text/html(UTF-8)`,
                "<h1>this is bit response</h1>"
              )
            )
          }
        }
      )

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println("hello world2")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
