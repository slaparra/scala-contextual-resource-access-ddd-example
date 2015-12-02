package kmruiz.cli

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

/**
  * @author kevin 
  * @since 12/2/15.
  */
object CLI {
  implicit object SingleThreadExecutionContext extends ExecutionContext {
    def execute(runnable: Runnable): Unit = runnable.run()
    def reportFailure(t: Throwable): Unit = throw t
  }

  def main(argv: Array[String]) {
    val passport = Await.result(InitCommandLine(argv).credentials(), 1 second)
    var line: String = ""
    do {
      print("$> ")
      line = readLine()
      CommandLine(passport, line).process()
    } while (line != "exit")
  }
}
