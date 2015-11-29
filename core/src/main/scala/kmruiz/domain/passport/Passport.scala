package kmruiz.domain.passport

import java.util.Date
import com.github.nscala_time.time.Imports._
import kmruiz.domain.resource.{ResourceId, ResourceAccessor}

/**
  * @author kevin 
  * @since 11/24/15.
  */
trait Passport extends ResourceAccessor {
  def refresh(): Passport
}

trait PassportCreator {
  def createPassport(): Passport
}

case class ExpirablePassport(username: String, expirationDate: Date, roles: Seq[String]) extends Passport {
  require(username.trim.length > 0, "username must not be empty")
  require(expirationDate.after(new Date), "passport has been expired")
  require(roles.nonEmpty, "passport must have roles")

  def this(username: String, roles: Seq[String]) = this(username, DateTime.now + 5.minute toDate, roles)

  def refresh() = new ExpirablePassport(username, roles)
  def generateResourceId(uuid: String) = ResourceId(uuid, roles)
}

object Passport {
  def apply(username: String, expirationDate: Date, roles: Seq[String]): ExpirablePassport = ExpirablePassport(username, expirationDate, roles)
  def apply(username: String, roles: Seq[String]): ExpirablePassport = new ExpirablePassport(username, roles)
}
