package examples

/**
 * Created by kanly
 */
class Rational(numer: Int, denom: Int) {
  require(denom >= 0)
  private val g = gcd(numer, denom)
  val numerator = numer / g
  val denominator = denom / g

  def this(natural: Int) = this(natural, 1)

  def add(that: Rational) =
    new Rational(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)

  def +(that: Rational) = add(that)

  def multiply(that: Rational) =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def *(that: Rational) = multiply(that)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString = numer + "/" + denom

}

object Rational {
  implicit def intToRational(int: Int) = new Rational(int)
}
