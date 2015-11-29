import java.util.NoSuchElementException

import play.api.GlobalSettings
import play.api.mvc.RequestHeader
import play.api.mvc.Results._
import kmruiz.domain.transformers.implicits.MonadTransformers._
/**
  * @author kevin 
  * @since 11/29/15.
  */
object Global extends GlobalSettings {
  override def onError(request: RequestHeader, ex: Throwable) = ex match {
    case e: NoSuchElementException => NotFound(views.html.RegistrationBookController.index(Seq("Invalid credentials!")))
    case e: IllegalArgumentException => BadRequest(views.html.RegistrationBookController.index(Seq(e.getMessage)))
  }
}
