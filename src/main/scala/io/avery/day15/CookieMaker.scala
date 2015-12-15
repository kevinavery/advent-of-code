package io.avery.day15

object CookieMaker {
  case class Ingredient(capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int)

  def findMaxCookieScore(input: List[String], teaspoons: Int): Int = {
    val ingredients = parseIngredients(input)
    choices(teaspoons, ingredients.keys.toList, Map())
      .map(accumulate(ingredients, _))
      .map(score)
      .max
  }

  def findMaxCookieScoreWithCalories(input: List[String], teaspoons: Int, requiredCalories: Int): Int = {
    val ingredients = parseIngredients(input)
    choices(100, ingredients.keys.toList, Map())
      .map(accumulate(ingredients, _))
      .filter(_.calories == requiredCalories)
      .map(score)
      .max
  }

  def parseIngredients(input: List[String]): Map[String, Ingredient] = {
    val pattern = "(.+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (\\d+)".r

    input.map {
      case pattern(name, cap, dur, flav, tex, cal) => name -> Ingredient(cap.toInt, dur.toInt, flav.toInt, tex.toInt, cal.toInt)
    }.toMap
  }

  def score(sup: Ingredient): Int = {
    Math.max(sup.capacity, 0) * Math.max(sup.durability, 0) * Math.max(sup.flavor, 0) * Math.max(sup.texture, 0)
  }

  def accumulate(ingredients: Map[String, Ingredient], amounts: Map[String, Int]): Ingredient = {
    amounts.foldLeft(Ingredient(0, 0, 0, 0, 0)) {
      case ((acc), (name, amt)) => {
        val i = ingredients(name)
        acc.copy(capacity = acc.capacity + i.capacity * amt)
          .copy(durability = acc.durability + i.durability * amt)
          .copy(flavor = acc.flavor + i.flavor * amt)
          .copy(texture = acc.texture + i.texture * amt)
          .copy(calories = acc.calories + i.calories * amt)
      }
    }
  }

  def choices(remainingAmt: Int, remainingIngredients: List[String], combination: Map[String, Int]): List[Map[String, Int]] = {
    remainingIngredients match {
      case Nil => {
        List(combination).filter(_.values.sum == 100)
      }
      case head :: rest => {
        0.to(remainingAmt).map { amt =>
          choices(remainingAmt - amt, rest, combination.updated(head, amt))
        }.toList
      }.flatten
    }
  }
}
