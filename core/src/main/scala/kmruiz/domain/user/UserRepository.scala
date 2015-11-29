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
  def apply(users: Map[String, User])(implicit ec: ExecutionContext): UserRepository = VolatileUserRepository(users)
  def apply()(implicit ec: ExecutionContext): UserRepository = VolatileUserRepository(Map(
    "user1" -> User("user1", "user1", Seq("ROLE_1")),
    "user2" -> User("user2", "user2", Seq("ROLE_2")),
    "user3" -> User("user3", "user3", Seq("ROLE_3")),
    "user4" -> User("user4", "user4", Seq("ROLE_1", "ROLE_2", "ROLE_3"))
  ))
}
