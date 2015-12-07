package io.avery.day5

import org.scalatest.{Matchers, FlatSpec}

class NiceStringVerifierSpec extends FlatSpec with Matchers {
  import NiceStringVerifier._

  "A NiceStringVerifier" should "verify nice strings" in {
    isNiceString("ugknbfddgicrmopn") should be(true)
    isNiceString("aaa") should be(true)
    isNiceString("jchzalrnumimnmhp") should be(false)
    isNiceString("haegwjzuvuyypxyu") should be(false)
    isNiceString("dvszwmarrgswjxmb") should be(false)
  }

  it should "verify new nice strings" in {
    isNewNiceString("qjhvhtzxzqqjkmpb") should be(true)
    isNewNiceString("xxyxx") should be(true)
    isNewNiceString("uurcxstgmygtbstg") should be(false)
    isNewNiceString("ieodomkazucvgmuy") should be(false)
  }

  it should "verify repeat" in {
    hasRepeat("aa", 0) should be(true)
    hasRepeat("abc", 0) should be(false)
    hasRepeat("abc", 1) should be(false)
    hasRepeat("abc", 5) should be(false)
    hasRepeat("aaa", 1) should be(true)
    hasRepeat("xyx", 1) should be(true)
    hasRepeat("abcdefeghi", 1) should be(true)
  }

  it should "verify non-overlapping double pairs" in {
    hasDoublePair("xyxy") should be(true)
    hasDoublePair("aaa") should be(false)
    hasDoublePair("aabcdefgaa") should be(true)
  }
}
