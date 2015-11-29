package kmruiz.domain.user

import scala.concurrent.{ExecutionContext, Future}
import kmruiz.domain.transformers.implicits.MonadTransformers._

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait UserService {
  def login(username: String, password: String): Future[AuthenticatedUser]
}

case class BasicUserService(userRepository: UserRepository)(implicit ec: ExecutionContext) extends UserService {
  def login(username: String, password: String) = for (
    user <- userRepository.findUser(username) ;
    logged <- user.login(password)
  ) yield logged
}

object UserService {
  def apply(userRepository: UserRepository)(implicit ec: ExecutionContext): UserService = BasicUserService(userRepository)
  def apply()(implicit ec: ExecutionContext): UserService = BasicUserService(UserRepository())
}
