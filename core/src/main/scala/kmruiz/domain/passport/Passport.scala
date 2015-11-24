package kmruiz.domain.passport

import java.util.Date
import com.github.nscala_time.time.Imports._

/**
  * @author kevin 
  * @since 11/24/15.
  */
trait Passport {
  def refresh(): ExpirablePassport
}

case class ExpirablePassport(username: String, expirationDate: Date) extends Passport {
  require(username.trim.length > 0, "username must not be empty")

  def refresh() = copy(expirationDate = DateTime.now + 5.minute toDate)
}

object Passport {
  def apply(username: String, expirationDate: Date): ExpirablePassport = ExpirablePassport(username, expirationDate)
}