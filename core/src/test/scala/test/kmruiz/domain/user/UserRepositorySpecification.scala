package test.kmruiz.domain.user

import kmruiz.domain.user.{User, UserRepository}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author kevin 
  * @since 11/25/15.
  */
class UserRepositorySpecification extends FlatSpec with Matchers {
  import test._

  "A UserRepository" must "find a user by it's username" in {
    for (
      user <- repository.findUser(existingUsername)
    ) yield user should not be null
  }

  def repository = UserRepository(Map(existingUsername -> sampleUser))
  def sampleUser = User(existingUsername, "123456")
  def existingUsername = "mufasa"
}
test/kmruiz/domain/user/UserRepositorySpecification.scala:23
