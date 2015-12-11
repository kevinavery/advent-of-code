package io.avery.day11

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  val passwords = PasswordGenerator.getPasswords(input)
  println(passwords.head)
  println(passwords.tail.head)
}
