package test.kmruiz.domain.user

import kmruiz.domain.user.UserService
import org.scalatest.{Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/29/15.
  */
case class UserServiceSpecification() extends FlatSpec with Matchers {
  import test._

  "A UserService" must "fail when the user is not in the repository" in {
    intercept[NoSuchElementException] {
      monadic {
        UserService().login("IDoNotExist", "paswd")
      }
    }
  }

  it must "fail when the credentials are invalid" in {
    intercept[IllegalArgumentException] {
      monadic {
        UserService().login("user1", "anotherPwd")
      }
    }
  }

  it must "return the logged user" in {
    monadic {
      for (
        user <- UserService().login("user1", "user1")
      ) yield user should not be null
    }
  }
}
