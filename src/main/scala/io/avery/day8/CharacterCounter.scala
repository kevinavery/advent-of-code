package io.avery.day8

object CharacterCounter {

  def computeInMemoryStringDelta(lines: List[String]): Int = {
    val all = lines.mkString
    val stripped = all.replaceAll("\\\\x[0-9a-f]{1,2}", "λ")
      .replaceAll("\\\\\\\\", "λ")
      .replaceAll("\\\\\"", "λ")
      .replaceAll("\"", "")
    all.length - stripped.length
  }

  def computeEscapedLiteralDelta(lines: List[String]): Int = {
    val all = lines.mkString
    val escaped = all.replaceAll("\\\\x[0-9a-f]{1,2}", "λλλλλ")
      .replaceAll("\\\\\\\\", "λλλλ")
      .replaceAll("\\\\\"", "λλλλ")
      .replaceAll("\"", "λλλ")
    escaped.length - all.length
  }
}
