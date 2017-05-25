import BookControllerError.PageParam


object Sandbox extends App{

  val possibly: Either[Int, List[String]] = Left(50)


println(possibly.left.map(x => x))


}

sealed abstract class BookControllerError

object BookControllerError {

  case class
  ContentRepository(error: ContentRepositoryError) extends BookControllerError

  case class Xslt(error: ExternalXslt) extends BookControllerError

  case class PageParam(page: String) extends BookControllerError

}



case class ContentRepositoryError(error: Error)

case class ExternalXslt(error: Error)

