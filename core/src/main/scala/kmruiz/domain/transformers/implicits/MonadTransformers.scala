package kmruiz.domain.transformers.implicits

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * @author kevin 
  * @since 11/29/15.
  */
object MonadTransformers {
  implicit def tryToFuture[T](t: Try[T]): Future[T] = t match {
    case Success(v) => Future.successful(v)
    case Failure(ex) => Future.failed(ex)
  }

  implicit def bindToFuture[T](t: T): Future[T] = Future.successful(t)
}
