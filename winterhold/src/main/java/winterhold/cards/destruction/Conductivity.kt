package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.powers.ConductivityPower

class Conductivity : AbstractDestructionCard(
    ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE
) {

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, ConductivityPower(p, if (upgraded) 2 else 0)))
    }

    override fun upgrade() {
        if (!upgraded) {
            useUpgradeDescription()
            upgradeName()
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Conductivity::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Power.png")

        private const val COST = 0
    }
}