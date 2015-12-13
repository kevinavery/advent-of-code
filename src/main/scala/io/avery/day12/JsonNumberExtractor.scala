package io.avery.day12

import net.liftweb.json._

object JsonNumberExtractor {

  def sumAllNumbers(input: String): Int = {
    extractNumbers(parse(input), Set()).sum.toInt
  }

  def sumAllNumbersWithoutRed(input: String): Int = {
    extractNumbers(parse(input), Set(JString("red"))).sum.toInt
  }

  def extractNumbers(json: JValue, fieldBlacklist: Set[JValue]): List[Double] = json match {
    case JDouble(n) => List(n)
    case JInt(n) => List(n.toDouble)
    case JArray(elements) => elements.map(extractNumbers(_, fieldBlacklist)).flatten
    case JObject(fields) => if (fields.exists(field => fieldBlacklist.contains(field.value))) {
      Nil
    } else {
      fields.map(field => extractNumbers(field.value, fieldBlacklist)).flatten
    }
    case _ => Nil
  }
}
