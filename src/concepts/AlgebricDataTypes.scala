package concepts

object Foo {

  def barBoolean(b: Boolean): String = if (b) "Hello" else "Bye" // cardinality = 2
  def barInt(i: Int): String = s"My int is $i" // cardinality = Int.MaxValue - Int.MinValue + 1 = 4294967296

  sealed trait Base // Sum type
  case class BooleanVersion(b: Boolean) extends Base
  case class IntVersion(i: Int) extends Base

  // cardinality = Int.MaxValue - Int.MinValue + 1 + 2
  def bar(b: Base): String = b match {
    case BooleanVersion(b) => if (b) "Hello" else "Bye"
    case IntVersion(i) => s"My int is $i"
  }

  def unsafeBar(a: Any): String = a match {
    case b: Boolean => if (b) "Hello" else "Bye"
    case i: Int => s"My int is $i"
  }

  // Product type: cardinality: 2 * 2 * 4294967296
  case class Forecast(isGonnaRain: Boolean, isGonnaBeCold: Boolean, celsius: Int)


  def weatherRant(forecast: Forecast): String = (forecast match {
    case Forecast(true, true, _) => "Meah, always raining in London! Brrr!"
    case Forecast(true, false, _) => "Meah, always raining in London! I'll go for a run anyway"
    case Forecast(false, true, _) => "I'll wear a jacket"
    case Forecast(false, false, _) => "Ooohoo! It's nice weather"
  }) + ". And it's gonna be " + forecast.celsius + "C"

  def forecastDump(f: Forecast) = f.toString


  def main(args: Array[String]): Unit = {
    println(barBoolean(true))
    println(barInt(3))

    println(bar(BooleanVersion(false)))
    println(bar(IntVersion(38)))

    println(unsafeBar(false))
    println(unsafeBar(38))

    println(weatherRant(Forecast(isGonnaRain = false, isGonnaBeCold = false, celsius = 30)))

    println("Space of Int has cardinality of " + (BigInt(Int.MaxValue) - Int.MinValue + 1))
  }
}