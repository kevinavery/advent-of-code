package io.avery.day17

object EggnogContainerChooser {

  def countContainerCombinations(input: List[String], litersOfEggnog: Int): Int = {
    combinations(parseInput(input), Nil).count(_.sum == litersOfEggnog)
  }

  def countMinContainerCombinations(input: List[String], litersOfEggnog: Int): Int = {
    val c = combinations(parseInput(input), Nil).filter(_.sum == litersOfEggnog)
    val minNumContainers = c.map(_.size).min
    c.count(_.size == minNumContainers)
  }

  def parseInput(input: List[String]): List[Int] = {
    input.map(_.toInt)
  }

  def combinations(remainingChoices: List[Int], combination: List[Int]): List[List[Int]] = {
    remainingChoices match {
      case Nil => List(combination)
      case head :: tail =>
        combinations(tail, head :: combination) ++ combinations(tail, combination)
    }
  }
}
