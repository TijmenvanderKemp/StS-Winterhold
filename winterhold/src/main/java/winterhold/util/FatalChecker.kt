package winterhold.util

import com.megacrit.cardcrawl.monsters.AbstractMonster

object FatalChecker {
    fun isFatal(target: AbstractMonster) =
        (target.isDying || target.currentHealth <= 0)
                && !target.halfDead
                && !target.hasPower("Minion")
}