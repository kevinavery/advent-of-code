package io.avery.day21

import scala.annotation.tailrec

object RpgSimulator {
  case class CharacterName(str: String)
  object CharacterNames {
    val Boss = CharacterName("boss")
    val Player = CharacterName("player")
  }
  case class Character(name: CharacterName, hitPoints: Int, damage: Int, armor: Int)
  case class StoreItem(cost: Int, damage: Int, armor: Int)

  val Weapons = List(
    StoreItem(8, 4, 0),
    StoreItem(10, 5, 0),
    StoreItem(25, 6, 0),
    StoreItem(40, 7, 0),
    StoreItem(74, 8, 0)
  )

  val Armor = List(
    StoreItem(13, 0, 1),
    StoreItem(31, 0, 2),
    StoreItem(53, 0, 3),
    StoreItem(75, 0, 4),
    StoreItem(102, 0, 5)
  )

  val Rings = List(
    StoreItem(25, 1, 0),
    StoreItem(50, 2, 0),
    StoreItem(100, 3, 0),
    StoreItem(20, 0, 1),
    StoreItem(40, 0, 2),
    StoreItem(80, 0, 3)
  )

  def cheapestWinner(input: List[String]): Int = {
    computePlayers.filter(c => simulateGame(c._2, parseBoss(input)).name == CharacterNames.Player).map(_._1).min
  }

  def mostExpensiveLoser(input: List[String]): Int = {
    computePlayers.filter(c => simulateGame(c._2, parseBoss(input)).name == CharacterNames.Boss).map(_._1).max
  }

  def computePlayers: List[(Int, Character)] = {
    chooseItems.map { choices =>
      val character = choices.foldLeft(Character(CharacterNames.Player, 100, 0, 0)) { (c, item) =>
        c.copy(damage = c.damage + item.damage, armor = c.armor + item.armor)
      }

      (choices.map(_.cost).sum, character)
    }
  }

  @tailrec
  def simulateGame(attacker: Character, defender: Character): Character = {
    val lostHitPoints = Math.max(1, attacker.damage - defender.armor)
    val newDefender = defender.copy(hitPoints = defender.hitPoints - lostHitPoints)
    if (newDefender.hitPoints < 1) {
      attacker
    } else {
      simulateGame(newDefender, attacker)
    }
  }

  def parseBoss(input: List[String]): Character = {
    val hitPointsPattern = "Hit Points: (\\d+)".r
    val damagePattern = "Damage: (\\d+)".r
    val armorPattern = "Armor: (\\d+)".r

    input.foldLeft(Character(CharacterNames.Boss, 0, 0, 0)) { (c, line) =>
      line match {
        case hitPointsPattern(n) => c.copy(hitPoints = n.toInt)
        case damagePattern(n) => c.copy(damage = n.toInt)
        case armorPattern(n) => c.copy(armor = n.toInt)
      }
    }
  }

  def chooseItems: List[List[StoreItem]] = {
    val ringChoices = combinations(Rings).filter(_.size < 3)

    Weapons.flatMap { weapon =>
      Armor.flatMap { armor =>
        ringChoices.flatMap { rings =>
          List(weapon :: armor :: rings, weapon :: rings)
        }
      }
    }
  }

  def combinations[T](choices: List[T]): List[List[T]] = {
    def rec(choices: List[T], combination: List[T]): List[List[T]] = {
      choices match {
        case Nil => List(combination)
        case head :: tail => rec(tail, head :: combination) ++ rec(tail, combination)
      }
    }

    rec(choices, Nil)
  }
}
