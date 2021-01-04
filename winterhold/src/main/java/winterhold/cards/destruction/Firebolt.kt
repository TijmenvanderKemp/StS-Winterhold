package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.cards.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageType

class Firebolt : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY
) {
    // Actions the card should do.
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FIRE,
                attackEffect = AbstractGameAction.AttackEffect.FIRE
            )
        )
    }

    //Upgraded stats.
    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DMG)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Firebolt::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 6
        private const val UPGRADE_PLUS_DMG = 3
    }

    // /STAT DECLARATION/
    init {
        baseDamage = DAMAGE
    }
}