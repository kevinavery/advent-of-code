package io.avery.day1

import scala.annotation.tailrec

object FloorFinder {

  def findEndFloor(instructions: String): Int = {
    computeFloors(instructions).head
  }

  def findFirstBasementPosition(instructions: String): Int = {
    computeFloors(instructions).reverse.indexOf(-1)
  }

  def computeFloors(instructions: String): List[Int] = {
    @tailrec
    def solveRec(index: Int, floors: List[Int]): List[Int] = {
      if (index == instructions.length) {
        floors
      } else if (instructions.charAt(index) == '(') {
        solveRec(index + 1, floors.head + 1 :: floors)
      } else if (instructions.charAt(index) == ')') {
        solveRec(index + 1, floors.head - 1 :: floors)
      } else {
        throw new IllegalArgumentException
      }
    }

    solveRec(0, List(0))
  }
}
