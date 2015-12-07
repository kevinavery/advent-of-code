package io.avery.day7

object CircuitEmulator {

  def solve(instructions: List[String], name: String): Int = {
    val expressionMap = {
      val pattern = "(.+) -> ([a-z]+)".r
      instructions.map {
        case pattern(expression, name) => (name -> expression)
      }.toMap
    }

    var cache = Map[String, Int]()

    def eval(nameOrValue: String): Int = {
      val constantPattern = "(\\d+)".r
      val wirePattern = "([a-z]+)".r
      val notPattern = "NOT (.+)".r
      val andPattern = "(.+) AND (.+)".r
      val orPattern = "(.+) OR (.+)".r
      val lshiftPattern = "(.+) LSHIFT (\\d+)".r
      val rshiftPattern = "(.+) RSHIFT (\\d+)".r

      cache.get(nameOrValue) match {
        case Some(cachedValue) => cachedValue
        case None => expressionMap.get(nameOrValue) match {
          case None => nameOrValue.toInt
          case Some(expression) => {
            val result = (expression match {
              case constantPattern(c) => c.toInt
              case wirePattern(x) => eval(x)
              case notPattern(x) => ~eval(x)
              case andPattern(a, b) => eval(a) & eval(b)
              case orPattern(a, b) => eval(a) | eval(b)
              case lshiftPattern(x, amt) => eval(x) << amt.toInt
              case rshiftPattern(x, amt) => eval(x) >> amt.toInt
            }) & 0xFFFF
            cache = cache.updated(nameOrValue, result)
            result
          }
        }
      }
    }

    eval(name)
  }
}
