package io.avery.day14

import scala.annotation.tailrec

object ReindeerRacer {
  case class ReindeerSpecs(speed: Int, movingTime: Int, restTime: Int)

  def winnerByDistance(input: List[String], raceTime: Int): Int = {
    parseSpecs(input).map {
      case((name, info)) => dist(info, raceTime, true, 0)
    }.max
  }

  def winnerByPoints(input: List[String], raceTime: Int): Int = {
    val reindeerSpecs = parseSpecs(input)

    1.to(raceTime).foldLeft(Map[String, Int]()) { (points, t) =>
      val results = reindeerSpecs.map {
        case ((name, info)) => (name -> dist(info, t, true, 0))
      }

      val winners = results.filter(_._2 == results.map(_._2).max).map(_._1)
      winners.foldLeft(points) { (points, name) =>
        points.updated(name, points.getOrElse(name, 0) + 1)
      }
    }.map(_._2).max
  }

  def parseSpecs(input: List[String]): Map[String, ReindeerSpecs] = {
    val pattern = "(.+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\.".r

    input.map {
      case pattern(name, a, b, c) => name -> ReindeerSpecs(a.toInt, b.toInt, c.toInt)
    }.toMap
  }

  @tailrec
  def dist(info: ReindeerSpecs, timeRemaining: Int, moving: Boolean, distanceTraveled: Int): Int = {
    if (timeRemaining <= 0) {
      distanceTraveled
    } else if (moving) {
      val removeTime = Math.min(timeRemaining, info.movingTime)
      dist(info, timeRemaining - removeTime, !moving, distanceTraveled + info.speed * removeTime)
    } else {
      val removeTime = Math.min(timeRemaining, info.restTime)
      dist(info, timeRemaining - removeTime, !moving, distanceTraveled)
    }
  }
}
