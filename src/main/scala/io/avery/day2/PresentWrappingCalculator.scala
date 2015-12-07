package io.avery.day2

object PresentWrappingCalculator {

  def computeTotalWrappingPaperArea(sizeList: List[String]): Int = {
    sizeList.map(parseDimensions).map {
      case (l, w, h) => computeWrappingPaperArea(l, w, h)
    }.sum
  }

  def computeTotalRibbonLength(sizeList: List[String]): Int = {
    sizeList.map(parseDimensions).map {
      case (l, w, h) => computeRibbonLength(l, w, h)
    }.sum
  }

  def parseDimensions(line: String): (Int, Int, Int) = {
    val dimensionPattern = "(\\d+)x(\\d+)x(\\d+)".r
    line match {
      case dimensionPattern(l, w, h) => (l.toInt, w.toInt, h.toInt)
    }
  }

  def computeWrappingPaperArea(length: Int, width: Int, height: Int): Int = {
    val sides = List(length * width, width * height, length * height)
    sides.map(_ * 2).sum + sides.min
  }

  def computeRibbonLength(length: Int, width: Int, height: Int): Int = {
    val sorted = List(length, width, height).sorted
    val shortestDim = sorted.head
    val secondShortestDim = sorted.tail.head
    2 * (shortestDim + secondShortestDim) + (length * width * height)
  }
}
