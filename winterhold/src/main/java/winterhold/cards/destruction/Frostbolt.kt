package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.WeakPower
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.cards.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageType

class Frostbolt : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY
) {
    // Actions the card should do.
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FROST,
                attackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
            )
        )
        AbstractDungeon.actionManager.addToBottom(
            ApplyPowerAction(
                m, p, WeakPower(m, magicNumber, false)
            )
        )
    }

    //Upgraded stats.
    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        @kotlin.jvm.JvmField
        val ID: String = WinterholdMod.makeID(Frostbolt::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 5
        private const val MAGIC_NUMBER = 1
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    // /STAT DECLARATION/
    init {
        baseDamage = DAMAGE
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
    }
}