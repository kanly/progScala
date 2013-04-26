package examples

import Element.elem

abstract class Element(content: Array[String]) {
  def contents: Array[String] = content

  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = elem(widen(that.width).contents ++ that.widen(width).contents)

  def beside(that: Element): Element =
    elem(
      for (
        (line1, line2) <- heighten(that.height).contents zip that.heighten(height).contents
      ) yield line1 + line2
    )

  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  def heighten(h: Int) =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

  override def toString = contents mkString "\n"
}

private class ArrayElement(content: Array[String]) extends Element(content)

private class LineElement(content: String) extends Element(Array(content))

private class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element(Array.fill(height)(ch.toString * width))


object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(ch: Char, width: Int, height: Int): Element = new UniformElement(ch, width, height)

  def elem(line: String): Element = new LineElement(line)
}


object Spiral {
  val space = elem(" ")
  val corner = elem("+")

  def spiral(nEdges: Int, direction: Int): Element =
    nEdges match {
      case 1 => corner
      case _ => {
        val sp = spiral(nEdges - 1, (direction + 3) % 4)
        def vBar = elem('|', 1, sp.height)
        def hBar = elem('-', sp.width, 1)
        direction match {
          case 0 => (corner beside hBar) above (sp beside space)
          case 1 => (sp above space) beside (corner above vBar)
          case 2 => (space beside sp) above (hBar beside corner)
          case _ => (vBar above corner) beside (space above sp)
        }
      }
    }

  def main(args: Array[String]) {
    val nSides = args(0).toInt
    println(nSides)
    println(spiral(nSides, 2))
  }
}