package kmruiz.domain.passport

import scala.concurrent.{ExecutionContext, Future}
import kmruiz.domain.transformers.implicits.MonadTransformers._

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait PassportService {
  def createPassport(passportCreator: PassportCreator): Future[Passport]
  def findPassport(uuid: String): Future[Passport]
}

case class BasicPassportService(passportRepository: PassportRepository)(implicit ec: ExecutionContext) extends PassportService {
  def createPassport(passportCreator: PassportCreator) = for (
    passport <- passportCreator.createPassport() ;
    saved <- passportRepository.save(passport)
  ) yield saved

  def findPassport(uuid: String) = passportRepository.findByUser(uuid)
}

object PassportService {
  def apply(repository: PassportRepository)(implicit ec: ExecutionContext): PassportService = BasicPassportService(repository)
  def apply()(implicit ec: ExecutionContext): PassportService = BasicPassportService(PassportRepository())
}
