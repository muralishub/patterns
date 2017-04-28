package typeClass

import typeClass.Example.NumberLike
//Steps to create type classes
//1, define behavior C as trait
//2, Provide default implementations for your types
//3, call the behavior of C in common way

object Example {

 // 1,
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def divide(x: T, t: Int): T
    def minus(x: T, y: T): T
  }

  // 2, Create companion object with trait
  object NumberLike{

    implicit object NumberLikeDouble extends NumberLike[Double] {
      override def plus(x: Double, y: Double): Double = x + y
      override def divide(x: Double, t: Int): Double = x / t
      override def minus(x: Double, y: Double): Double = x - y
    }

    implicit object NumberLikeInt extends NumberLike[Int] {
      override def plus(x: Int, y: Int): Int = x + y
      override def divide(x: Int, t: Int): Int = x / t
      override def minus(x: Int, y: Int): Int = x - y
    }
  }

}




object Statistics extends App{
// 3a : call
  def mean[T](numbers: Seq[T])(implicit number: NumberLike[T]): T = {
    number.divide(numbers.reduce(number.plus), numbers.size)
  }

  //another way to write this is using context bounds

  def mean2[T: NumberLike](numbers: Seq[T]) = {
    val number = implicitly[NumberLike[T]]
    number.divide(numbers.reduce(number.plus), numbers.size)
  }

// we can simply call it like this
  val result = mean(List(1, 2, 3))
  println(result)


// this is neat but we can make use of implicit class to have mean method available to list
  //so we can simply call List(1, 2, 3).mean




  // now call type class with an implicit class


  import NumberOps._
  val l = List(1, 4, 3)

  println(l.mean)



}


//Implicit class

object NumberOps {
implicit class SeqNumberOps[T](numbers: Seq[T]) {
  def mean(implicit number: NumberLike[T]) = {
    number.divide(numbers.reduce(number.plus), numbers.size)
  }
}
}


