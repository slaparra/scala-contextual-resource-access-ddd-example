package kmruiz.api

import kmruiz.application.RegistrationBook
import play.api.mvc.{Request, Action, Controller}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author kevin 
  * @since 11/29/15.
  */
object RegistrationBookController extends Controller {
  val registrationBook = RegistrationBook()

  def index = Action {
    Ok(views.html.RegistrationBookController.index())
  }

  def forbidPassport = Action {
    Ok("").withNewSession
  }

  def createPassport = Action.async(parse.tolerantFormUrlEncoded) { implicit request =>
    val username = param("username")
    val password = param("password")

    (username, password) match {
      case (Some(user), Some(pass)) =>
        registrationBook.sign(user, pass)
          .map(passport => Redirect("/resources").withSession("passport" -> passport.toString))
      case _ => Future.failed(new NoSuchElementException("Invalid credentials!"))
    }
  }

  def param(name: String)(implicit request: Request[Map[String, Seq[String]]]): Option[String] = {
    request.body.get(name).map(_.head).filter(_.nonEmpty)
  }

}
