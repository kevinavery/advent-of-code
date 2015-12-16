package io.avery.day16

object AuntSueFinder {
  case class Sue(name: String, things: Map[String, Int])

  val SuesThings = Map(
    "children" -> 3,
    "cats" -> 7,
    "samoyeds" -> 2,
    "pomeranians" -> 3,
    "akitas" -> 0,
    "vizslas" -> 0,
    "goldfish" -> 5,
    "trees" -> 3,
    "cars" -> 2,
    "perfumes" -> 1
  )

  def findSueUsingMFCSAM(input: List[String]): Option[String] = {
    findMatchingSue(parseSues(input), { (thingName, thingNum) =>
      SuesThings(thingName) == thingNum
    }).map(_.name)
  }

  def findSueUsingRetroEncabulator(input: List[String]): Option[String] = {
    findMatchingSue(parseSues(input), { (thingName, thingNum) =>
      if (Set("cats", "trees").contains(thingName)) {
        SuesThings(thingName) < thingNum
      } else if (Set("pomeranians", "goldfish").contains(thingName)) {
        SuesThings(thingName) > thingNum
      } else {
        SuesThings(thingName) == thingNum
      }
    }).map(_.name)
  }

  def parseSues(input: List[String]): List[Sue] = {
    val pattern = "Sue (\\d+): (.+): (\\d+), (.+): (\\d+), (.+): (\\d+)".r

    input.map {
      case pattern(s, a, anum, b, bnum, c, cnum) => {
        Sue(s, Map(a -> anum.toInt, b -> bnum.toInt, c -> cnum.toInt))
      }
    }
  }

  def findMatchingSue(allSues: List[Sue], predicate: (String, Int) => Boolean): Option[Sue] = {
    allSues.find(sue => sue.things.forall { case (thingName, thingNum) => predicate(thingName, thingNum) })
  }
}
