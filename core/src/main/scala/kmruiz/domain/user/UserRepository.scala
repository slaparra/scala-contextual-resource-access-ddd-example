package kmruiz.domain.user

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author kevin 
  * @since 11/25/15.
  */
trait UserRepository {
  def findUser(username: String): Future[User]
}

case class VolatileUserRepository(users: Map[String, User])(implicit ec: ExecutionContext) extends UserRepository {
  def findUser(username: String) = Future { users(username) }
}

object UserRepository {
  def apply(users: Map[String, User])(implicit ec: ExecutionContext): VolatileUserRepository = VolatileUserRepository(users)
}
