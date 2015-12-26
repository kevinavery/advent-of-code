package io.avery.day25

object WeatherMachine {

  def getCode(input: String): Long = {
    val (row, col) = parseInput(input)
    computeCode(20151125L, row, col)
  }

  def parseInput(input: String): (Int, Int) = {
    val pattern = ".* row (\\d+), column (\\d+)\\.".r
    input match {
      case pattern(row, col) => (row.toInt, col.toInt)
    }
  }

  def computeCode(start: Long, row: Int, col: Int): Long = {
    1.to(toIndex(row, col) - 1).foldLeft(start) { (acc, _) =>
      next(acc)
    }
  }

  def next(n: Long): Long = {
    n * 252533 % 33554393
  }

  def toIndex(row: Int, col: Int): Int = {
    1.to(col + row - 1).foldLeft(0)(_ + _) - (row - 1)
  }
}
