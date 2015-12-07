package io.avery.day5

object NiceStringVerifier {

  def countNiceStrings(strings: List[String]): Int = {
    strings.count(isNiceString)
  }

  def countNewNiceStrings(strings: List[String]): Int = {
    strings.count(isNewNiceString)
  }

  def isNiceString(s: String): Boolean = {
    containsNVowels(s, 3) && hasRepeat(s, 0) && !List("ab", "cd", "pq", "xy").exists(s.contains)
  }

  def isNewNiceString(s: String): Boolean = {
    hasDoublePair(s) && hasRepeat(s, 1)
  }

  def containsNVowels(s: String, n: Int): Boolean = {
    s.filter("aeiou".toSet.contains).size >= n
  }

  def hasRepeat(s: String, skipSize: Int): Boolean = {
    0.until(s.length - 1 - skipSize).exists(i => s.charAt(i) == s.charAt(i + 1 + skipSize))
  }

  def hasDoublePair(s: String): Boolean = {
    0.until(s.length - 2).exists(i => s.substring(i + 2).contains(s.substring(i, i + 2)))
  }
}
