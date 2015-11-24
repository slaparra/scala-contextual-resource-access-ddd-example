package kmruiz.domain.user

/**
  * @author kevin 
  * @since 11/24/15.
  */
trait User {

}

case class PlainUser(username: String, password: String) {
  require(username.trim.length > 0, "username must not be empty")
  require(password.trim.length > 0, "password must not be empty")
}

object User {
  def apply(username: String, password: String): PlainUser = PlainUser(username, password)
}
