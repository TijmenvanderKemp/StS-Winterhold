package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.powers.FireRunePower
import winterhold.spelldamage.SpellDamageTags.DEALS_FIRE_DAMAGE

class FireRune : AbstractDestructionCard(
    ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE
) {


    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, block))

        addToBot(
            ApplyPowerAction(
                p, p, FireRunePower(p, damage),
                damage
            )
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
        val ID: String = WinterholdMod.makeID(FireRune::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("${FireRune::class.java.simpleName}.png")

        private const val COST = 2
        private const val DAMAGE = 4
        private const val UPGRADE_PLUS_DAMAGE = 3
        private const val BLOCK = 4
        private const val UPGRADE_PLUS_BLOCK = 3
    }

    init {
        baseBlock = BLOCK
        baseDamage = DAMAGE
        tags.add(DEALS_FIRE_DAMAGE)
    }
}