//
//
//object Decoders {
//  trait StringDecoder[A] {
//    def fromString(string: String): Either[String, A]
//  }
//
//  implicit val colorTypeStringDecoder = new StringDecoder[Color] {
//    def fromString(value: String) = {
//      val colours = List("red", "green", "blue")
//      if (colours.contains(value)) Right(Color(value))
//      else
//        Left("not a valid color name")
//    }
//  }
//
//
//  object StringSyntax {
//    implicit class StringDecoderOps(value: String) {
//      def decodeTo[A](implicit decoder: StringDecoder[A]) = {
//        decoder.fromString(value)
//      }
//    }
//  }
//
//}
//
//
//object Main extends App {
//
//  import Decoders._
//
//  println("red".decodeTo[Color])
//
//
//
//}