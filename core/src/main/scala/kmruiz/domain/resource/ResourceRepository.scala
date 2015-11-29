package kmruiz.domain.resource

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait ResourceRepository {
  def findResourceById(resourceId: ResourceId): Future[Resource]
}

case class VolatileResourceRepository(resources: Seq[PrivateResource])(implicit ec: ExecutionContext) extends ResourceRepository {
  def findResourceById(resourceId: ResourceId) = {
    resources.find(r => r.id == resourceId.id && r.accessibleByRoles.intersect(resourceId.roles).nonEmpty).fold {
      Future.failed[Resource](new NoSuchElementException(resourceId.toString))
    } { r =>
      Future.successful(r)
    }
  }
}

object ResourceRepository {
  def apply(resources: Seq[PrivateResource])(implicit ec: ExecutionContext): ResourceRepository = VolatileResourceRepository(resources)
}
