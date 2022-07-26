package infrastructure

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.json.RootJsonFormat

final case class BidRequest(id: String)
final case class BidResponse(id: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val bidRequest: RootJsonFormat[BidRequest] = jsonFormat1(BidRequest)
  implicit val bidResponse: RootJsonFormat[BidResponse] = jsonFormat1(
    BidResponse
  )
}
