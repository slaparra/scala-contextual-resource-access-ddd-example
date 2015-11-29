package test.kmruiz.domain.resource

import kmruiz.domain.resource.{PrivateResource, ResourceId, ResourceRepository}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author kevin 
  * @since 11/29/15.
  */
case class ResourceRepositorySpecification() extends FlatSpec with Matchers {
  import test._

  "A ResourceRepository" must "fail on not finding an object" in {
    intercept[NoSuchElementException] {
      monadic {
        emptyResourceRepository.findResourceById(nonExistingResourceId)
      }
    }
  }

  it must "return the resource when the id and a role matches" in {
    monadic {
      for (
        resource <- fullResourceRepository.findResourceById(existingResourceId)
      ) yield resource should not be null
    }
  }

  it must "fail when finding an object by id but not having the required role" in {
    intercept[NoSuchElementException] {
      monadic {
        for (
          resource <- fullResourceRepository.findResourceById(notMatchingResourceId)
        ) yield resource
      }
    }
  }

  def existingResourceId = ResourceId("1", Seq("ADMIN"))
  def notMatchingResourceId = ResourceId("1", Seq("USER"))
  def fullResourceRepository = ResourceRepository(Seq(PrivateResource("1", Seq("ADMIN"))))
  def nonExistingResourceId = ResourceId("", Seq())
  def emptyResourceRepository = ResourceRepository(Seq())

}
