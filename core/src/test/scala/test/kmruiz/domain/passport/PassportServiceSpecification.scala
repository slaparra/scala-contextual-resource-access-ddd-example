package test.kmruiz.domain.passport

import kmruiz.domain.passport.{ExpirablePassport, Passport, PassportCreator, PassportService}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author kevin 
  * @since 11/29/15.
  */
case class PassportServiceSpecification() extends FlatSpec with Matchers {
  import test._

  "A PassportService" must "register a passport and it must be findable" in {
    val ps = PassportService()
    monadic {
      for (
        passport <- ps.createPassport(new PassportCreator {
          def createPassport() = Passport("username", Seq("some role"))
        });
        foundPassport <- ps.findPassport("username")
      ) yield passport should not be null
    }
  }
}
