package io.avery.day15

import org.scalatest.{FlatSpec, Matchers}

class CookieMakerSpec extends FlatSpec with Matchers {
  import CookieMaker._

  val input =
    """
      |Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
      |Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
    """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

  "A CookieMaker" should "find max cookie score" in {
    findMaxCookieScore(input, 100) should be(62842880)
  }

  it should "find max cookie score with calorie requirement" in {
    findMaxCookieScoreWithCalories(input, 100, 500) should be(57600000)
  }
}
