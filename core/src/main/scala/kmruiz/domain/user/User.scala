package kmruiz.domain.user

import kmruiz.domain.passport.{Passport, PassportCreator}

import scala.util.Try

/**
  * @author kevin 
  * @since 11/24/15.
  */
trait User {
  def login(inputPassword: String): Try[AuthenticatedUser]
}

trait AuthenticatedUser extends User with PassportCreator

case class DefaultUser(username: String, password: String) extends User {
  require(username.trim.length > 0, "username must not be empty")
  require(password.trim.length > 0, "password must not be empty")

  def login(inputPassword: String) = Try(PlainAuthenticatedUser(username, password, inputPassword))
}

case class PlainAuthenticatedUser(username: String, password: String, providedPassword: String) extends AuthenticatedUser {
  require(password == providedPassword, "password do not match")

  def login(inputPassword: String) = Try(copy(providedPassword = inputPassword))
  def createPassport() = Passport(username)
}

object User {
  def apply(username: String, password: String): DefaultUser = DefaultUser(username, password)
}
