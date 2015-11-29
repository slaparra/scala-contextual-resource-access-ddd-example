package kmruiz.application

import kmruiz.domain.passport.{PassportService, Passport}
import kmruiz.domain.user.UserService

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait RegistrationBook {
  def login(username: String, password: String): Future[Passport]
}

case class BasicRegistrationBook(userService: UserService, passportService: PassportService)(implicit ec: ExecutionContext)
  extends RegistrationBook {
  def login(username: String, password: String) = for (
    user <- userService.login(username, password) ;
    passport <- passportService.createPassport(user)
  ) yield passport
}

object RegistrationBook {
  def apply(userService: UserService, passportService: PassportService)(implicit ec: ExecutionContext): RegistrationBook = {
    BasicRegistrationBook(userService, passportService)
  }

  def apply()(implicit ec: ExecutionContext): RegistrationBook = {
    BasicRegistrationBook(UserService(), PassportService())
  }
}
