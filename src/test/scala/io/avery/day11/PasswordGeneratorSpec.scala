package io.avery.day11

import org.scalatest.{Matchers, FlatSpec}

class PasswordGeneratorSpec extends FlatSpec with Matchers {
  import PasswordGenerator._

  "A PasswordGenerator" should "validate passwords" in {
    isValid("hijklmmn") should be(false)
    isValid("abbceffg") should be(false)
    isValid("abbcegjk") should be(false)
    isValid("abcdffaa") should be(true)
    isValid("ghjaabcc") should be(true)
  }

  it should "generate next passwords" in {
    getPasswords("abcdefgh").head should be("abcdffaa")
    getPasswords("ghijklmn").head should be("ghjaabcc")
  }
}
