package routes

import akka.http.scaladsl.marshalling.ToResponseMarshallable._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.google.inject.{Inject, Singleton}

@Singleton
class Routes @Inject()() {

  lazy val routes: Route = {
    path("healthcheck") {
      get {
        complete(OK)
      }
    }
  }

}
