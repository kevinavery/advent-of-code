package io.avery.day3

import scala.annotation.tailrec

object InfiniteHousesSolver {
  case class HouseLocation(x: Int, y: Int)

  def countVisitedHouses(directions: String): Int = {
    computeLocations(directions).toSet.size
  }

  def computeVisitedHousesWithRoboSanta(directions: String): Int = {
    directions.zipWithIndex.foldLeft(("", "")) {
      case ((santaDirections, roboSantaDirections), (char, index)) => {
        if (index % 2 == 0) {
          (santaDirections + char, roboSantaDirections)
        } else {
          (santaDirections, roboSantaDirections + char)
        }
      }
    } match {
      case (santaDirections, roboSantaDirections) =>
        (computeLocations(santaDirections).toSet ++ computeLocations(roboSantaDirections).toSet).size
    }
  }

  def computeLocations(directions: String): List[HouseLocation] = {
    @tailrec
    def solveRec(index: Int, locations: List[HouseLocation]): List[HouseLocation] = {
      if (index == directions.length) {
        locations
      } else {
        val HouseLocation(x, y) = locations.head
        val next = directions.charAt(index) match {
          case '^' => HouseLocation(x, y + 1)
          case 'v' => HouseLocation(x, y - 1)
          case '>' => HouseLocation(x + 1, y)
          case '<' => HouseLocation(x - 1, y)
        }
        solveRec(index + 1, next :: locations)
      }
    }

    solveRec(0, List(HouseLocation(0, 0)))
  }
}
