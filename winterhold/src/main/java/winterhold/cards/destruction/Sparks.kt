package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageRandomEnemyAction
import winterhold.cards.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageType

class Sparks : AbstractDestructionCard(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(
            SpellDamageRandomEnemyAction(
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.SHOCK,
                AbstractGameAction.AttackEffect.NONE
            )
        )
    }

    //Upgraded stats.
    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Sparks::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 8
        private const val UPGRADE_PLUS_DAMAGE = 3
    }

    init {
        baseDamage = DAMAGE
    }
}