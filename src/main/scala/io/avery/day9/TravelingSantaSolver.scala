package io.avery.day9

object TravelingSantaSolver {
  case class Segment(from: String, to: String, dist: Int)

  def findMinRouteDistance(lines: List[String]): Int = {
    buildAllRoutes(lines).map(routeToDistance).min
  }

  def findMaxRouteDistance(lines: List[String]): Int = {
    buildAllRoutes(lines).map(routeToDistance).max
  }

  def routeToDistance(route: List[Segment]): Int = {
    route.map(_.dist).sum
  }

  def buildAllRoutes(lines: List[String]): List[List[Segment]] = {
    val pattern = "(.+) to (.+) = (\\d+)".r

    val allSegments = lines.toList.map {
      case pattern(from, to, dist) => {
        List(Segment(from, to, dist.toInt), Segment(to, from, dist.toInt))
      }
    }.flatten

    val allCities = allSegments.map(_.to).toSet

    def solveRec(fromCity: String, visitedSegments: List[Segment]): List[List[Segment]] = {
      if (visitedSegments.size == allCities.size - 1) {
        List(visitedSegments)
      } else {
        allSegments.filter(_.from == fromCity).filterNot(s => visitedSegments.map(_.from).contains(s.to)).map { s =>
          solveRec(s.to, s :: visitedSegments)
        }.flatten
      }
    }

    allCities.toList.map(solveRec(_, Nil)).flatten
  }
}
