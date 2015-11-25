import scala.concurrent.{Await, Future, ExecutionContext}
import scala.concurrent.duration._

/**
  * @author kevin 
  * @since 11/25/15.
  */
package object test {
  implicit object SingleThreadExecutionContext extends ExecutionContext {
    def execute(runnable: Runnable) = runnable.run()
    def reportFailure(t: Throwable) = throw t
  }

  def monadic(a: => Future[Any]): Unit = Await.result(a, 1 second)
}
