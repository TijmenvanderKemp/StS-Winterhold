package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Frostbolt : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FROST,
                attackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
            )
        )
        addToBot(
            GainBlockAction(p, block)
        )
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
            upgradeBlock(UPGRADE_PLUS_BLOCK)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Frostbolt::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 5
        private const val UPGRADE_PLUS_DAMAGE = 2
        private const val BLOCK = 5
        private const val UPGRADE_PLUS_BLOCK = 2
    }

    // /STAT DECLARATION/
    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}