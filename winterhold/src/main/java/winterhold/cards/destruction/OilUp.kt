package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.AbstractDestructionCard
import winterhold.powers.FrostResistancePower
import winterhold.powers.spelldamagevulnerable.FireVulnerablePower

class OilUp : AbstractDestructionCard(
    ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(ApplyPowerAction(m, p, FrostResistancePower(m, magicNumber, false)))
        addToBot(ApplyPowerAction(m, p, FireVulnerablePower(m, magicNumber, false)))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        @kotlin.jvm.JvmField
        val ID: String = WinterholdMod.makeID(OilUp::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Skill.png")

        private const val COST = 1
        private const val MAGIC_NUMBER = 2
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    init {
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
    }
}