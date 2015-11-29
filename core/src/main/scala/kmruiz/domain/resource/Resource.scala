package kmruiz.domain.resource

/**
  * @author kevin 
  * @since 11/29/15.
  */
trait Resource
case class ResourceId(id: String, roles: Seq[String])

trait ResourceAccessor {
  def generateResourceId(uuid: String): ResourceId
}

case class PrivateResource(id: String, accessibleByRoles: Seq[String]) extends Resource {
  require(id != null && id.trim.length > 0, "id must not be empty")
  require(accessibleByRoles != null, "null roles must be an empty sequence")
}

object Resource {
  def apply(id: String, roles: Seq[String]): Resource = PrivateResource(id, roles)
}
