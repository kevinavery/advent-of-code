package io.avery.day10

object LookAndSayGame {

  def nthLength(in: String, n: Int): Int = {
    1.to(n).foldLeft(in)((s, _) => next(s)).length
  }

  def next(in: String): String = {
    val groups = in.zipWithIndex.tail.foldLeft(List(List(in.head))) {
      case (l, (c, i)) => if (in.charAt(i - 1) == c) {
        (c :: l.head) :: l.tail
      } else {
        List(c) :: l
      }
    }
    groups.reverse.map(l => s"${l.size}${l.head}").mkString
  }
}
