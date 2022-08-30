package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{
  as,
  complete,
  concat,
  entity,
  get,
  path,
  post
}
import akka.stream.ActorMaterializer

import akka.http.scaladsl.server.Route
import infrastructure.{BidRequest, BidResponse, JsonSupport}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object Server extends App with JsonSupport {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val route: Route =
    concat(
      path("hello") {
        get {
          val foo = 3
          system.log.info("foobar")
          complete(
            HttpEntity(
              ContentTypes.`text/html(UTF-8)`,
              "<h1>Say Hello to akka-http</h1>"
            )
          )
        }
      },
      path("bid") {
        post {
          entity(as[BidRequest]) { bidRequest =>
            complete(BidResponse(s"${bidRequest.id}"))
          }
        }
      }
    )

  val bindingFuture: Future[Http.ServerBinding] =
    Http().bindAndHandle(route, "localhost", 8080)

  println("hello world2")
//  StdIn.readLine() // let it run until user presses return
//  bindingFuture
//    .flatMap(_.unbind()) // trigger unbinding from the port
//    .onComplete(_ => system.terminate()) // and shutdown when done
  bindingFuture
    .onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info(
          "Server online at http://{}:{}/",
          address.getHostString,
          address.getPort
        )
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
}
