package winterhold.cards.destruction

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.actions.AbstractGameAction
import winterhold.WinterholdMod
import winterhold.cards.AbstractDestructionCard

class StrikeDestruction : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY
) {
    // Actions the card should do.
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
            DamageAction(
                m, DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL
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
        @kotlin.jvm.JvmField
        val ID: String = WinterholdMod.makeID(StrikeDestruction::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 5
        private const val UPGRADE_PLUS_DMG = 3
    }

    // /STAT DECLARATION/
    init {
        baseDamage = DAMAGE
    }
}