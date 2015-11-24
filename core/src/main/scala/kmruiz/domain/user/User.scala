package kmruiz.domain.user

import scala.util.Try

/**
  * @author kevin 
  * @since 11/24/15.
  */
trait User {
  def login(inputPassword: String): Try[User]
}

case class PlainUser(username: String, password: String) extends User {
  require(username.trim.length > 0, "username must not be empty")
  require(password.trim.length > 0, "password must not be empty")

  def login(inputPassword: String) = Try(AuthenticatedUser(username, password, inputPassword))
}

case class AuthenticatedUser(username: String, password: String, providedPassword: String) extends User {
  require(password == providedPassword, "password do not match")

  def login(inputPassword: String) = Try(copy(providedPassword = inputPassword))
}

object User {
  def apply(username: String, password: String): PlainUser = PlainUser(username, password)
}
