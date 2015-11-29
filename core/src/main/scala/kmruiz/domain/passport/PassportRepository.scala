package kmruiz.domain.passport

import java.util.Date

import scala.concurrent.{ExecutionContext, Future}
import scala.collection.mutable.{Map => MMap}
/**
  * @author kevin 
  * @since 11/25/15.
  */
trait PassportRepository {
  def save(passport: Passport): Future[Passport]
  def findByUser(username: String): Future[Passport]
}

case class PassportSerialization(username: String, date: Date, roles: Seq[String])
case class VolatilePassportRepository(state: MMap[String, PassportSerialization])(implicit ec: ExecutionContext) extends PassportRepository {
  def save(passport: Passport) = Future {
    val kPassport = passport.asInstanceOf[ExpirablePassport]
    state.put(kPassport.username, PassportSerialization(kPassport.username, kPassport.expirationDate, kPassport.roles))
    passport
  }

  def findByUser(username: String) = Future {
    state.get(username).map(s => Passport(s.username, s.date, s.roles)).get
  }
}

object PassportRepository {
  def apply(state: MMap[String, PassportSerialization])(implicit ec: ExecutionContext): VolatilePassportRepository = VolatilePassportRepository(state)
  def apply()(implicit ec: ExecutionContext): VolatilePassportRepository = VolatilePassportRepository(MMap())
}
