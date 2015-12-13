package io.avery.day13

object DinnerTableArranger {
  case class PersonPair(a: String, b: String) {
    def reverse = PersonPair(b, a)
  }

  def getMaximalHappiness(input: List[String]): Int = {
    val hm = buildHappinessMap(input)
    computeHappiestTable(hm, getAllPeople(hm))
  }

  def getMaximalHappinessWithMe(input: List[String]): Int = {
    val hm = buildHappinessMap(input)
    computeHappiestTable(hm, "me" :: getAllPeople(hm))
  }

  def buildHappinessMap(input: List[String]): Map[PersonPair, Int] = {
    val pattern = "(.+) would (gain|lose) (\\d+) happiness units by sitting next to (.+)\\.".r

    input.map {
      case pattern(personA, sign, amt, personB) => {
        val happinessDelta = if (sign == "gain") amt.toInt else -amt.toInt
        PersonPair(personA, personB) -> happinessDelta
      }
    }.toMap
  }

  def getAllPeople(happinessMap: Map[PersonPair, Int]): List[String] = {
    happinessMap.keys.map(p => List(p.a, p.b)).toList.flatten.distinct
  }

  def computeHappiestTable(happinessMap: Map[PersonPair, Int], people: List[String]): Int = {
    people.permutations.map { table =>
      PersonPair(table.head, table.last) :: table.sliding(2).toList.map(p => PersonPair(p.head, p.last))
    }.map { table =>
      table.map { pair =>
        happinessMap.getOrElse(pair, 0) + happinessMap.getOrElse(pair.reverse, 0)
      }.sum
    }.max
  }
}
