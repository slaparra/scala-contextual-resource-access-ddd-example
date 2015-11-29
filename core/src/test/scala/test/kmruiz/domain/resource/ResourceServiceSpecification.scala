package test.kmruiz.domain.resource

import kmruiz.domain.resource.{ResourceId, ResourceAccessor, ResourceService}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author kevin 
  * @since 11/29/15.
  */
case class ResourceServiceSpecification() extends FlatSpec with Matchers {
  import test._

  "A ResourceService" must "find an accessible resource" in {
    val rs = ResourceService()
    monadic {
      for (
        resource <- rs.access(new ResourceAccessor {
          def generateResourceId(uuid: String) = ResourceId(uuid, Seq("ROLE_1"))
        }, "1")
      ) yield resource should not be null
    }
  }
}
