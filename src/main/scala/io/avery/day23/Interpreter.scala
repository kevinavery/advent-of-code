package io.avery.day23

import scala.annotation.tailrec

object Interpreter {

  def interpret(instructions: Vector[String], regs: Map[String, Int]): Map[String, Int] = {
    val hlfPattern = "hlf ([a-z]+)".r
    val tplPattern = "tpl ([a-z]+)".r
    val incPattern = "inc ([a-z]+)".r
    val jmpPattern = "jmp ([+-]?\\d+)".r
    val jiePattern = "jie ([a-z]+), ([+-]?\\d+)".r
    val jioPattern = "jio ([a-z]+), ([+-]?\\d+)".r

    @tailrec
    def rec(idx: Int, regs: Map[String, Int]): Map[String, Int] = {
      if (idx < 0 || idx >= instructions.size) {
        regs
      } else {
        instructions(idx) match {
          case hlfPattern(r) => rec(idx + 1, regs.updated(r, regs(r) / 2))
          case tplPattern(r) => rec(idx + 1, regs.updated(r, regs(r) * 3))
          case incPattern(r) => rec(idx + 1, regs.updated(r, regs(r) + 1))
          case jmpPattern(offset) => rec(idx + offset.toInt, regs)
          case jiePattern(r, offset) => rec(if (regs(r) % 2 == 0) idx + offset.toInt else idx + 1, regs)
          case jioPattern(r, offset) => rec(if (regs(r) == 1) idx + offset.toInt else idx + 1, regs)
        }
      }
    }

    rec(0, regs)
  }
}
