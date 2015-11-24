import scala.concurrent.ExecutionContext

/**
  * @author kevin 
  * @since 11/25/15.
  */
package object test {
  implicit object SingleThreadExecutionContext extends ExecutionContext {
    def execute(runnable: Runnable) = runnable.run()
    def reportFailure(t: Throwable) = throw t
  }
}
