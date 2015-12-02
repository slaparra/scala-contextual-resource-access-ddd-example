package kmruiz.cli

import kmruiz.application.{RegistrationBook, SecurityService}
import org.rogach.scallop.{ScallopConf, Subcommand}

import scala.concurrent.{ExecutionContext, Future}
/**
  * @author kevin 
  * @since 12/2/15.
  */
case class InitCommandLine(argv: Array[String])(implicit ec: ExecutionContext) extends ScallopConf(argv) {
  val registrationBook = RegistrationBook()

  val username = opt[String]("username", 'U')
  val password = opt[String]("password", 'P')

  def credentials(): Future[String] = (username.get, password.get) match {
    case (Some(username), Some(password)) => for (
      passport <- registrationBook.sign(username, password)
    ) yield passport.DTO.id
    case _ => Future.failed(new IllegalArgumentException("username and password are required!"))
  }
}

case class CommandLine(passport: String, line: String)(implicit ec: ExecutionContext) extends ScallopConf(line.split(" ")) {
  val securityService = SecurityService()

  val access = new Subcommand("access") {
    val resource = opt[String]("resource", 'R')
  }

  def process() = access.resource.get match {
    case Some(resource) => for (
      data <- securityService.accessResource(passport, resource)
    ) yield println("You accessed resource " + data)
  }
}
