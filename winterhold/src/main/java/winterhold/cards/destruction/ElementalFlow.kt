package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.powers.GainStrengthWhenComboReachesMultipleOfFivePower

class ElementalFlow : AbstractDestructionCard(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, GainStrengthWhenComboReachesMultipleOfFivePower(p, strengthAmount = 1)))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBaseCost(UPGRADE_NEW_COST)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(ElementalFlow::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Power.png")

        private const val COST = 3
        private const val UPGRADE_NEW_COST = 2
        private const val MAGIC_NUMBER = 5
    }

    init {
        magicNumber = MAGIC_NUMBER
        baseMagicNumber = MAGIC_NUMBER
    }
}