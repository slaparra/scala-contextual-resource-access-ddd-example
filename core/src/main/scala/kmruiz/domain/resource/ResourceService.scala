package kmruiz.domain.resource

import scala.concurrent.{ExecutionContext, Future}
import kmruiz.domain.transformers.implicits.MonadTransformers._
/**
  * @author kevin 
  * @since 11/29/15.
  */
trait ResourceService {
  def access(accessor: ResourceAccessor, resource: String): Future[Resource]
}

case class BasicResourceService(resourceRepository: ResourceRepository)(implicit ec: ExecutionContext) extends ResourceService {
  def access(accessor: ResourceAccessor, resource: String) = for (
    resourceId <- accessor.generateResourceId(resource) ;
    resource <- resourceRepository.findResourceById(resourceId)
  ) yield resource
}

object ResourceService {
  def apply(resourceRepository: ResourceRepository)(implicit ec: ExecutionContext): ResourceService = BasicResourceService(resourceRepository)
  def apply()(implicit ec: ExecutionContext): ResourceService = BasicResourceService(ResourceRepository())
}
