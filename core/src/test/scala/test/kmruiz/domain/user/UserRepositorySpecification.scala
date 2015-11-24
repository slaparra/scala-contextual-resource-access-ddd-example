package test.kmruiz.domain.user

import kmruiz.domain.user.{User, UserRepository}
import org.scalatest.{Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/25/15.
  */
class UserRepositorySpecification extends FlatSpec with Matchers {
  import test._

  "A UserRepository" must "find a user by it's username" in {
    for (
      user <- UserRepository(Map("mufasa" -> User("mufasa", "123456"))).findUser("mufasa")
    ) yield user should not be null
  }
}
