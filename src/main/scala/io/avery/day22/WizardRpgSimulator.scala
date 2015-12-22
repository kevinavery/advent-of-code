package io.avery.day22

object WizardRpgSimulator {
  trait Character
  case class Boss(hitPoints: Int, damage: Int) extends Character
  case class Wizard(hitPoints: Int, mana: Int) extends Character

  case class Effect(cost: Int)

  object MagicMissile extends Effect(53)
  object Drain extends Effect(73)
  object Shield extends Effect(113)
  object Poison extends Effect(173)
  object Recharge extends Effect(229)

  case class Timer(effect: Effect, turnsRemaining: Int)

  def findMinWinningMana(input: List[String]): Int = {
    simulateGame(Wizard(50, 500), parseBoss(input), (_, w) => w).take(100000).min
  }

  def findMinWinningManaWithHandicap(input: List[String]): Int = {
    def handicap(wizardsTurn: Boolean, wiz: Wizard): Wizard = {
      if (wizardsTurn) {
        wiz.copy(hitPoints = wiz.hitPoints - 1)
      } else {
        wiz
      }
    }

    simulateGame(Wizard(50, 500), parseBoss(input), handicap).take(100000).min
  }

  def parseBoss(input: List[String]): Boss = {
    val hitPointsPattern = "Hit Points: (\\d+)".r
    val damagePattern = "Damage: (\\d+)".r

    input.foldLeft(Boss(0, 0)) { (boss, line) =>
      line match {
        case hitPointsPattern(n) => boss.copy(hitPoints = n.toInt)
        case damagePattern(n) => boss.copy(damage = n.toInt)
      }
    }
  }

  def shadow[T, R](x: T)(f: T => R): R = f(x)

  def simulateGame(wiz: Wizard, boss: Boss, handicap: (Boolean, Wizard) => Wizard): Stream[Int] = {
    // Optimization: Track minimum mana to prune subtrees.
    var minManaUsed = Int.MaxValue

    def rec(wizardsTurn: Boolean, wiz: Wizard, boss: Boss, timers: List[Timer], manaUsed: Int): Stream[Int] = {
      shadow(handicap(wizardsTurn, wiz)) { wiz =>
        if (wiz.hitPoints <= 0) {
          Stream()
        } else if (boss.hitPoints <= 0) {
          minManaUsed = Math.min(manaUsed, minManaUsed)
          Stream(manaUsed)
        } else {
          shadow(applyTimers(wiz, boss, timers)) { case (wiz, boss) =>
            if (boss.hitPoints <= 0) {
              minManaUsed = Math.min(manaUsed, minManaUsed)
              Stream(manaUsed)
            } else {
              shadow(trimTimers(timers)) { timers =>
                if (wizardsTurn) {
                  chooseEffects(wiz, timers.map(_.effect)).toStream.flatMap { effect =>
                    shadow(applyImmediateEffect(effect, wiz, boss, timers)) { case (wiz, boss, timers) =>
                      if (manaUsed < minManaUsed) {
                        rec(!wizardsTurn, wiz.copy(mana = wiz.mana - effect.cost), boss, timers, manaUsed + effect.cost)
                      } else {
                        Stream()
                      }
                    }
                  }
                } else {
                  val armor = if (timers.map(_.effect).contains(Shield)) 7 else 0
                  val lostHitPoints = Math.max(1, boss.damage - armor)
                  rec(!wizardsTurn, wiz.copy(hitPoints = wiz.hitPoints - lostHitPoints), boss, timers, manaUsed)
                }
              }
            }
          }
        }
      }
    }

    rec(true, wiz, boss, Nil, 0)
  }

  def chooseEffects(wiz: Wizard, activeEffects: List[Effect]): List[Effect] = {
    List(MagicMissile, Drain, Shield, Poison, Recharge).reverse.filter(_.cost <= wiz.mana).filterNot(activeEffects.contains)
  }

  def applyTimers(wiz: Wizard, boss: Boss, timers: List[Timer]): (Wizard, Boss) = {
    timers.foldLeft((wiz, boss)) {
      case ((wiz, boss), timer) => {
        timer.effect match {
          case Shield => (wiz, boss)
          case Poison => (wiz, boss.copy(hitPoints = boss.hitPoints - 3))
          case Recharge => (wiz.copy(mana = wiz.mana + 101), boss)
        }
      }
    }
  }

  def applyImmediateEffect(effect: Effect, wiz: Wizard, boss: Boss, timers: List[Timer]): (Wizard, Boss, List[Timer]) = {
    effect match {
      case MagicMissile => (wiz, boss.copy(hitPoints = boss.hitPoints - 4), timers)
      case Drain => (wiz.copy(hitPoints = wiz.hitPoints + 2), boss.copy(hitPoints = boss.hitPoints - 2), timers)
      case Shield => (wiz, boss, Timer(effect, 6) :: timers)
      case Poison => (wiz, boss, Timer(effect, 6) :: timers)
      case Recharge => (wiz, boss, Timer(effect, 5) :: timers)
    }
  }

  def trimTimers(timers: List[Timer]): List[Timer] = {
    timers.map(t => t.copy(turnsRemaining = t.turnsRemaining - 1)).filter(_.turnsRemaining > 0)
  }
}
