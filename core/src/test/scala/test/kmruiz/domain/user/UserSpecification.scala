package test.kmruiz.domain.user

import kmruiz.domain.user.User
import org.scalatest.{TryValues, Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/24/15.
  */
class UserSpecification extends FlatSpec with Matchers with TryValues {
  "An User" must "only be buildable with a valid username and a password" in {
    validUser // it will fail if it throws an exception
  }

  it must "have roles" in {
    intercept[IllegalArgumentException] {
      User("user1", "pwd1", Seq())
    }
  }

  it must "not accept an empty username" in {
    intercept[IllegalArgumentException] {
      User("", validPassword, validRoles)
    }
  }

  it must "not accept an empty password" in {
    intercept[IllegalArgumentException] {
      User(validUserName, "", validRoles)
    }
  }

  it must "login only when the password matches" in {
    validLoggedUser should be a 'success
  }

  it should "be able to login again" in {
    for (
      user <- validLoggedUser;
      loggedAgain <- user.login(validPassword)
    ) yield loggedAgain should be a 'success
  }


  it must "not be able to login with an invalid password" in {
    validUser.login(invalidPassword) should be a 'failure
  }

  "An AuthenticatedUser" must "be able to create a non expired passport" in {
    for (
      authUser <- validLoggedUser
    ) yield authUser.createPassport() // should not throw
  }

  def validUser = User(validUserName, validPassword, validRoles)
  def invalidPassword = "pwd2"
  def validPassword = "pwd1"
  def validUserName = "user1"
  def validLoggedUser = validUser.login(validPassword)
  def validRoles = Seq("USER")
}
