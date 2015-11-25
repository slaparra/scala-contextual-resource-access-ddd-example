package test.kmruiz.domain.passport

import com.github.nscala_time.time.Imports._
import kmruiz.domain.passport.Passport
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author kevin 
  * @since 11/24/15.
  */
class PassportSpecification extends FlatSpec with Matchers {
  "A Passport" must "only be buildable with a valid username" in {
    nonExpiredPassport // fails when throwing an exception
  }

  it must "not accept an empty role list" in {
    intercept[IllegalArgumentException] {
      Passport(validUser, nonExpiredDate, Seq())
    }
  }

  it must "not accept an empty username" in {
    intercept[IllegalArgumentException] {
      Passport("", nonExpiredDate, validRoles)
    }
  }

  it must "not accept an expired date" in {
    intercept[IllegalArgumentException] {
      Passport(validUser, expiredDate, validRoles)
    }
  }

  it must "when refreshed, update the expiration date ahead 5 minutes" in {
    // approximate it to 5 min with an error of 2 ms because of runtime
    nonExpiredPassport.refresh().expirationDate.getTime should be (fiveMinutesAhead.getTime +- 2)
  }

  def fiveMinutesAhead = (DateTime.now + 5.minute).toDate
  def nonExpiredDate = (DateTime.now + 1.second).toDate
  def expiredDate = DateTime.yesterday.toDate
  def validUser = "mufasa"
  def validRoles = Seq("Role")
  def nonExpiredPassport = Passport(validUser, nonExpiredDate, validRoles)
}
