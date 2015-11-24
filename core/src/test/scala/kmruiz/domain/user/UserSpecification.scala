package kmruiz.domain.user

import org.scalatest.{Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/24/15.
  */
class UserSpecification extends FlatSpec with Matchers {
  "A User" must "only be buildable with a valid username and a password" in {
    User("user1", "pwd1") // it will fail if it throws an exception
  }

  it must "not accept an empty username" in {
    intercept[IllegalArgumentException] {
      User("", "pwd1")
    }
  }
}
