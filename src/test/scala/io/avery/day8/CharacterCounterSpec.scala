package io.avery.day8

import org.scalatest.{FlatSpec, Matchers}

class CharacterCounterSpec extends FlatSpec with Matchers {
  import CharacterCounter._

  "A CharacterCounter" should "compute string literal vs in-memory string length delta" in {
    val input =
      """
        |""
        |"abc"
        |"aaa\"aaa"
        |"\x27"
      """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

    computeInMemoryStringDelta(input) should be(12)
  }

  it should "compute in-memory string vs escaped string literal length delta" in {
    val input =
      """
        |""
        |"abc"
        |"aaa\"aaa"
        |"\x27"
      """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

    computeEscapedLiteralDelta(input) should be(19)
  }
}
