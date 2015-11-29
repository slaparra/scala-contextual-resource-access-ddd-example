package kmruiz.application

import kmruiz.domain.passport.PassportService
import kmruiz.domain.resource.{ResourceService, Resource}

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait SecurityService {
  def accessResource(passportId: String, resource: String): Future[Resource]
}

case class BasicSecurityService(passportService: PassportService, resourceService: ResourceService)(implicit ec: ExecutionContext)
  extends SecurityService {
  def accessResource(passportId: String, resource: String) = for (
    passport <- passportService.findPassport(passportId) ;
    resource <- resourceService.access(passport, resource)
  ) yield resource
}

object SecurityService {
  def apply(passportService: PassportService, resourceService: ResourceService)(implicit ec: ExecutionContext): SecurityService = {
    BasicSecurityService(passportService, resourceService)
  }

  def apply()(implicit ec: ExecutionContext): SecurityService = {
    BasicSecurityService(PassportService(), ResourceService())
  }
}
