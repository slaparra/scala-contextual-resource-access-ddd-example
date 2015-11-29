package kmruiz.api

import java.util.NoSuchElementException

import kmruiz.application.SecurityService
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author kevin 
  * @since 11/29/15.
  */
object SecurityServiceController extends Controller {
  val securityService = SecurityService()

  def index = Action { request =>
    request.session.isEmpty match {
      case false => Ok(views.html.SecurityServiceController.profile())
      case true => Redirect("/registration")
    }
  }

  def resource(id: String) = Action.async { request =>
    request.session.get("passport") match {
      case Some(passport) => for (
        resource <- securityService.accessResource(passport, id)
      ) yield Ok(views.html.SecurityServiceController.resource(passport))
      case None => Future.failed(new NoSuchElementException("Invalid credentials!"))
    }
  }
}
