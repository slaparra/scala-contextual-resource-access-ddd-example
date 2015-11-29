package test.kmruiz.domain.resource

import kmruiz.domain.resource.PrivateResource
import org.scalatest.{Matchers, FlatSpec}

/**
  * @author kevin 
  * @since 11/29/15.
  */
case class ResourceSpecification() extends FlatSpec with Matchers {
  "A PrivateResource" must "not accept a null id" in {
    intercept[IllegalArgumentException] {
      PrivateResource(null, validRoleList)
    }
  }

  it must "not accept an empty id" in {
    intercept[IllegalArgumentException] {
      PrivateResource("", validRoleList)
    }
  }

  it must "not accept a null role list" in {
    intercept[IllegalArgumentException] {
      PrivateResource(validId, null)
    }
  }

  def validId = "id"
  def validRoleList = Seq.empty[String]
}
