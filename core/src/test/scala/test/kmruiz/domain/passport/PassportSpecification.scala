package test.kmruiz.domain.passport

import org.scalatest.{Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/24/15.
  */
class PassportSpecification extends FlatSpec with Matchers {
  "A Passport" must "only be buildable with a valid username" in {
    Passport("mufasa") // fails when throwing an exception
  }

  it must "not accept an empty username" in {
    intercept[IllegalArgumentException] {
      Passport("")
    }
  }
}
