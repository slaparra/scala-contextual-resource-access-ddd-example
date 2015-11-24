package test.kmruiz.domain.passport

import kmruiz.domain.passport.{Passport, PassportRepository}
import org.scalatest.{Matchers, FlatSpec}
import scala.collection.mutable.{Map => MMap}

/**
  * @author kevin 
  * @since 11/25/15.
  */
class PassportRepositorySpecification extends FlatSpec with Matchers {
  import test._

  "A PassportRepository" must "find a saved passport" in {
    val repo = repository
    for (
      passport <- repo.save(someValidPassport);
      foundPassport <- repo.findByUser(passportUser)
    ) yield passport should equal(foundPassport)
  }

  def passportUser = "somePassport"
  def someValidPassport = Passport(passportUser, Seq("roles"))
  def repository = PassportRepository(MMap())
}
